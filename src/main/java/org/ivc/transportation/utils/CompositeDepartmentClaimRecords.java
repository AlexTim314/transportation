/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivc.transportation.utils;

import java.util.List;
import lombok.Data;
import org.ivc.transportation.entities.Department;

/**
 *
 * @author nodata
 */
@Data
public class CompositeDepartmentClaimRecords {

    private Department department;
    private List<CompositeClaimRecord> compositeClaimRecords;

    public CompositeDepartmentClaimRecords(Department department, List<CompositeClaimRecord> compositeClaimRecords) {
        this.department = department;
        this.compositeClaimRecords = compositeClaimRecords;
    }
    public CompositeDepartmentClaimRecords(Department department) {
        this.department = department;
    }
}
