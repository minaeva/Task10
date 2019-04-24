package com.foxminded.dao;

import com.foxminded.model.StudentCard;
import java.util.List;

public interface StudentDao extends CrudDao<StudentCard> {

    List<StudentCard> findStudentsByGroupId(long groupId);
}
