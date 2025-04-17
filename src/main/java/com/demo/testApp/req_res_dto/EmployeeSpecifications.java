package com.demo.testApp.req_res_dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.demo.testApp.entities.Employee;
import com.demo.testApp.enums.Designation;

import jakarta.persistence.criteria.Predicate;

public class EmployeeSpecifications {

	public static Specification<Employee> withDynamicFilter(String name, Designation designation, Date joiningDateFrom,
			Date joiningDateTo, Long departmentId, Double minSalary, Double maxSalary) {

		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (name != null && !name.isEmpty()) {
				predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
			}

			if (designation != null) {
				predicates.add(cb.equal(root.get("designation"), designation));
			}

			if (joiningDateFrom != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("joiningDate"), joiningDateFrom));
			}

			if (joiningDateTo != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("joiningDate"), joiningDateTo));
			}

			if (departmentId != null) {
				predicates.add(cb.equal(root.get("department").get("id"), departmentId));
			}

			if (minSalary != null) {
				predicates.add(cb.greaterThanOrEqualTo(root.get("salary").get("amount"), minSalary));
			}

			if (maxSalary != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("salary").get("amount"), maxSalary));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
