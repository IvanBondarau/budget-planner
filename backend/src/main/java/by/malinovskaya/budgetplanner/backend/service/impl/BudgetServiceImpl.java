package by.malinovskaya.budgetplanner.backend.service.impl;

import by.malinovskaya.budgetplanner.backend.dao.BudgetDao;
import by.malinovskaya.budgetplanner.backend.dao.BudgetItemDao;
import by.malinovskaya.budgetplanner.backend.dao.CategoryDao;
import by.malinovskaya.budgetplanner.backend.dto.BudgetDto;
import by.malinovskaya.budgetplanner.backend.dto.BudgetCreateDto;
import by.malinovskaya.budgetplanner.backend.dto.BudgetInfoDto;
import by.malinovskaya.budgetplanner.backend.dto.BudgetSearchDto;
import by.malinovskaya.budgetplanner.backend.dto.CategoryInfoDto;
import by.malinovskaya.budgetplanner.backend.dto.ItemInfoDto;
import by.malinovskaya.budgetplanner.backend.entity.Budget;
import by.malinovskaya.budgetplanner.backend.entity.BudgetItem;
import by.malinovskaya.budgetplanner.backend.entity.Category;
import by.malinovskaya.budgetplanner.backend.service.BudgetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetDao budgetDao;

    private final CategoryDao categoryDao;
    private final BudgetItemDao itemDao;

    private final ModelMapper mapper;

    public BudgetServiceImpl(BudgetDao budgetDao, CategoryDao categoryDao, BudgetItemDao itemDao, ModelMapper mapper) {
        this.budgetDao = budgetDao;
        this.categoryDao = categoryDao;
        this.itemDao = itemDao;
        this.mapper = mapper;
    }

    @Override
    public List<BudgetDto> findBudget(BudgetSearchDto dto) {
        return budgetDao.findByUserId(dto.getUserId()).stream().map(
            t -> mapper.map(t, BudgetDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public BudgetDto create(BudgetCreateDto dto) {
        Budget budget = mapper.map(dto, Budget.class);
        budget.setCreationDate(new Date());
        Long id = budgetDao.create(budget);
        budget.setId(id);
        return mapper.map(budget, BudgetDto.class);
    }

    @Override
    public void delete(Long id) {
        this.budgetDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BudgetInfoDto read(Long id) {
        List<CategoryInfoDto> incomes = transformToDtos(categoryDao.findByBudgetId(id, 0L));
        List<CategoryInfoDto> expenses = transformToDtos(categoryDao.findByBudgetId(id, 1L));
        Budget budget = budgetDao.read(id);
        BudgetInfoDto dto = mapper.map(budget, BudgetInfoDto.class);
        dto.setIncomes(incomes);
        dto.setExpenses(expenses);
        return dto;
    }


    private List<CategoryInfoDto> transformToDtos(List<Category> categories) {
        return categories.stream().map(
                category -> {
                    List<BudgetItem> items = itemDao.findByCategoryId(category.getId());
                    CategoryInfoDto info = mapper.map(
                            category, CategoryInfoDto.class
                    );
                    info.setItems(
                            items.stream().map(value -> mapper.map(value, ItemInfoDto.class)).collect(Collectors.toList())
                    );
                    return info;
                }
        ).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(BudgetInfoDto infoDto, Long id) {
        Budget oldBudget = budgetDao.read(id);
        categoryDao.deleteByBudgetId(oldBudget.getId());
        Stream.concat(infoDto.getIncomes().stream(), infoDto.getExpenses().stream())
                .forEach(
                        categoryInfoDto -> {
                            Category category = mapper.map(categoryInfoDto, Category.class);
                            category.setBudgetId(oldBudget.getId());
                            Long categoryId = categoryDao.create(category);
                            categoryInfoDto.getItems().forEach(
                                    itemInfoDto -> {
                                        BudgetItem item = mapper.map(itemInfoDto, BudgetItem.class);
                                        item.setCategoryId(categoryId);
                                        itemDao.create(item);
                                    }
                            );
                        }
                );

    }
}
