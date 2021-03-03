package com.programmersonly.mentorship.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Transactional(Transactional.TxType.MANDATORY)
public class TemplateRepositoryImpl implements TemplateRepository {

    private final TemplateDao templateDao;

    @Override
    public void create(Template template) {
        TemplateEntity templateEntity = TemplateEntity.builder()
                .fullName(template.getFullName())
                .status(template.getStatus())
                .userId(template.getUserId())
                .email(template.getEmail())
                .build();
        templateDao.save(templateEntity);
    }

    @Override
    public Optional<Template> findById(UUID templateId) {
        return templateDao.findById(templateId)
                .map(this::map);
    }

    @Override
    public void confirm(UUID templateId) {
        templateDao.updateTemplateStatus(templateId, TemplateStatus.CONFIRMED);
    }

    @Override
    public void deleteById(UUID templateId) {
        templateDao.deleteById(templateId);
    }

    private Template map(TemplateEntity templateEntity){
        return Template.builder()
                .email(templateEntity.getEmail())
                .fullName(templateEntity.getFullName())
                .id(templateEntity.getId())
                .status(templateEntity.getStatus())
                .userId(templateEntity.getUserId())
                .build();
    }
}
