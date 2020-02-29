package com.hexad.service.general;

import com.hexad.entity.BaseEntity;
import com.hexad.exception.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


/**
 * This class is a generic service to provides some method for simple CRUD API.
 * By using this class a programmer does not need to write create, find, delete and etc method for implementing a service CRUD
 *
 * @author R.Fattahi
 */
@Service
@Transactional(readOnly = false)
@FunctionalInterface
public interface GenericService<T extends BaseEntity<PK>, PK extends Serializable> {

    abstract JpaRepository<T, PK> getGenericRepo();

    /**
     * config default for searching by sample instance that JPA data provides for us.
     * a programmer can override this method in a service and change them according business.
     * In this case all of searching are according like but it is changeable.
     */
    default Example<T> getExample(T example) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example<T> exam = Example.of(example, exampleMatcher);
        return exam;
    }

    /**
     * Selects a entity by id.
     *
     * @param id
     * @return found entity
     * @throws EntityNotFoundException if no entity with the given id was found.
     */
    default T findById(PK id) {
        Optional<T> t = getGenericRepo().findById(id);
        if (t.isPresent()) {
            return t.get();
        } else {
            throw new EntityNotFoundException("Could not find entity with id: " + id);
        }
    }

    /**
     * Selects a entity by sample.
     *
     * @param example
     * @return found entity
     */
    default <S extends T> T findBySample(S example) {
        Optional<T> t = getGenericRepo().findOne(getExample(example));
        if (t.isPresent()) {
            return t.get();
        }
        return null;
    }

    /**
     * save and update an entity
     * @param entity
     */
    default T save(T entity) {
        return getGenericRepo().save(entity);
    }

    /**
     * delete by ID
     * @param id
     */
    default void delete(PK id) {
        T t = findById(id);
        getGenericRepo().delete(t);
    }

    /**
     * get all of records in data base
     */
    default List<T> findAll() {
        return getGenericRepo().findAll();
    }

    /**
     * get all of records by a sort field
     * @param sort
     */
    default List<T> findAll(Sort sort) {
        return getGenericRepo().findAll(sort);
    }

    /**
     * get all of records by a sample instance
     * @param example
     */
    default <S extends T> List<T> findAll(S example) {
        return getGenericRepo().findAll(getExample(example));
    }

    /**
     * get all of records by a sample instance and sort field
     * @param example, sort
     */
    default <S extends T> List<T> findAll(S example, Sort sort) {
        return getGenericRepo().findAll(getExample(example), sort);
    }

    /**
     * get records by a page number and page size
     * @param page, size
     */
    default <S extends T> Page<T> findAll(int page, int size) {
        return getGenericRepo().findAll(PageRequest.of(page, size));
    }

    /**
     * get records by a page number and page size and sort field
     * @param page, size, sort
     */
    default Page<T> findAll(int page, int size, Sort sort) {
        return getGenericRepo().findAll(PageRequest.of(page, size, sort));
    }

    /**
     * get records by a page number and page size and sort field
     * @param pageRequest
     */
    default Page<T> findAll(PageRequest pageRequest) {
        return getGenericRepo().findAll(pageRequest);
    }

    /**
     * get records by a page number and page size and sort field and simple instance
     *  @param pageRequest, example
     */
    default <S extends T> Page<T> findAll(PageRequest pageRequest, S example) {
        return getGenericRepo().findAll(getExample(example), pageRequest);
    }

    /**
     * get records by a page number and page size and sort field and some properties
     *  @param page, size, direction, ... properties
     */
    default Page<T> findAll(int page, int size, Direction direction, String... properties) {
        return getGenericRepo().findAll(PageRequest.of(page, size, direction, properties));
    }

    /**
     * get records by a page number and page size and simple instance
     *  @param page, size, direction, example
     */
    default <S extends T> Page<T> findAll(int page, int size, S example) {
        return getGenericRepo().findAll(getExample(example), PageRequest.of(page, size));
    }

    /**
     * get records by a page number and page size and sort field and some properties and a simple instance
     *  @param page, size, example, direction,... properties
     */
    default <S extends T> Page<T> findAll(int page, int size, S example, Direction direction, String... properties) {
        return getGenericRepo().findAll(getExample(example), PageRequest.of(page, size, direction, properties));
    }

    /**
     * get records by a page number and page size and sort field and a simple instance
     * @param page, size, example, sort
     */
    default <S extends T> Page<T> findAll(int page, int size, S example, Sort sort) {
        return getGenericRepo().findAll(getExample(example), PageRequest.of(page, size));
    }

}
