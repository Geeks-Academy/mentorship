package com.programmersonly.mentorship.mentors.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

//Todo przepisz testy
public class TestTemplateRepository /*implements TemplateEntityRepository*/ {
/*
  private Map<UUID, TemplateEntity> entities = new HashMap<>();

  @Override
  public <S extends TemplateEntity> S save(S entity) {
    entities.put(entity.getId(), entity);
    return entity;
  }

  @Override
  public <S extends TemplateEntity> Iterable<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<TemplateEntity> findById(UUID uuid) {
    return Optional.ofNullable(entities.get(uuid));
  }

  @Override
  public boolean existsById(UUID uuid) {
    return entities.get(uuid) != null;
  }

  @Override
  public Iterable<TemplateEntity> findAll() {
    return entities.values();
  }

  @Override
  public Iterable<TemplateEntity> findAllById(Iterable<UUID> iterable) {
    return null;
  }

  @Override
  public long count() {
    return entities.size();
  }

  @Override
  public void deleteById(UUID uuid) {
    entities.remove(uuid);
  }

  @Override
  public void delete(TemplateEntity templateEntity) {
    entities.remove(templateEntity.getId());
  }

  @Override
  public void deleteAll(Iterable<? extends TemplateEntity> iterable) {
  }

  @Override
  public void deleteAll() {
    entities = new HashMap<>();
  }

  public Optional<TemplateEntity> findByUserId(UUID uuid) {
    return entities.values().stream()
        .filter(entity -> entity.getUserId() == uuid)
        .findFirst();
  }*/
}
