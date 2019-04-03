package com.foxminded.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.foxminded.domain.Validator.*;

@Data
public class Journal {

    private List<Section> sections = new ArrayList<>();

    public Section createSection(String subjectName){
        if (StringUtils.isBlank(subjectName)) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }
            Section section = new Section(subjectName);
        sections.add(section);
        return section;
    }

    public Section findSection(String sectionName){
        return findObjectByNameIfExists(sections,
                section -> Objects.equals(section.getSectionName(), sectionName),
                "Section",
                sectionName);
    }

    public boolean removeSection(String sectionName){
        return sections.removeIf(section -> Objects.equals(section.getSectionName(), sectionName));
    }

    public double calculateAverageMark() {
        if (sections.size() == 0) {
            return 0;
        }
        double result = 0.0;
        int counter = 0;
        for (Section section: sections) {
            double midAverage = section.calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        return (result == 0) ? 0 : result/counter;
    }

    public void addMark(String studentName, String subjectName, int mark){
        Section section = findSection(subjectName);
        section.addMark(studentName, mark);
    }
}
