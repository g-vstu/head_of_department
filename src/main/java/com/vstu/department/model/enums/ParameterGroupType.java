package com.vstu.department.model.enums;

import com.vstu.department.exception.BusinessException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ParameterGroupType {

    STUDY(1), SCIENCE(2), OTHER(3);

    private Integer index;

    public static ParameterGroupType getByIndex(int index) {
        for (ParameterGroupType groupType : ParameterGroupType.values()) {
            if (groupType.index == index) {
                return groupType;
            }
        }
        throw new BusinessException("Parameter group type not found!");
    }
}
