package com.programmersonly.mentorship.template;

import com.programmersonly.mentorship.exception.ConfirmationConflictException;
import com.programmersonly.mentorship.exception.TemplateNotFoundException;
import com.programmersonly.mentorship.template.dto.CreateTemplateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
class TemplateServiceImpl implements TemplateService {

  private final TemplateRepository templateRepository;

  @Override
  public void create(CreateTemplateDto createTemplateDto) {
    Template template = Template.builder()
            .fullName(createTemplateDto.getFullName())
            .email(createTemplateDto.getEmail())
            .userId(createTemplateDto.getUserId())
            .status(TemplateStatus.CREATED)
            .build();
    templateRepository.create(template);
  }

  @Override
  public void confirm(UUID templateId) {
    Template template = templateRepository.findById(templateId)
            .filter(tmp -> tmp.getStatus().equals(TemplateStatus.CREATED))
            .orElseThrow(ConfirmationConflictException::new);
    templateRepository.confirm(template.getId());
  }

  @Override
  public void remove(UUID templateId) {
    // TODO Czy można usuwać wyłącznie zgody o statusie CREATED?????
    Template template = templateRepository.findById(templateId)
            .filter(tmp -> tmp.getStatus().equals(TemplateStatus.CREATED))
            .orElseThrow(TemplateNotFoundException::new);
    templateRepository.deleteById(template.getId());
  }
}
