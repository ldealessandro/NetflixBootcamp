package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.services.CategoryService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class CategoryServiceImpl implements CategoryService {


	@Autowired
	private CategoryRepository categoryRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<CategoryRest> getCategories() throws NetflixException {

		if(categoryRepository.findAll().isEmpty()) {
			throw new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CATEGORY);
		}
		
		return categoryRepository.findAll().stream().map(category -> modelMapper.map(category, CategoryRest.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryRest createCategories(final CategoryRest categoryRest) throws NetflixException {
		final Category category = modelMapper.map(categoryRest, Category.class);
		return modelMapper.map(categoryRepository.save(category), CategoryRest.class);
	}

	@Override
	public CategoryRest getCategoryById(final Long id) throws NetflixException {
		final Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CATEGORY));
		return modelMapper.map(category, CategoryRest.class);
	}

	@Override
	public CategoryRest getCategoryByName(final String name) throws NetflixException {
		final Category category = categoryRepository.findByName(name)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CATEGORY));
		return modelMapper.map(category, CategoryRest.class);
	}

}
