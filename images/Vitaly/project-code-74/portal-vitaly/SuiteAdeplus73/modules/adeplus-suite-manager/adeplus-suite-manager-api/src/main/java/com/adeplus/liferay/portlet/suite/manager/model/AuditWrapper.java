/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.adeplus.liferay.portlet.suite.manager.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Audit}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Audit
 * @generated
 */
public class AuditWrapper
	extends BaseModelWrapper<Audit> implements Audit, ModelWrapper<Audit> {

	public AuditWrapper(Audit audit) {
		super(audit);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditId", getAuditId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("action", getAction());
		attributes.put("description", getDescription());
		attributes.put("date", getDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditId = (Long)attributes.get("auditId");

		if (auditId != null) {
			setAuditId(auditId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String action = (String)attributes.get("action");

		if (action != null) {
			setAction(action);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}
	}

	/**
	 * Returns the action of this audit.
	 *
	 * @return the action of this audit
	 */
	@Override
	public String getAction() {
		return model.getAction();
	}

	/**
	 * Returns the audit ID of this audit.
	 *
	 * @return the audit ID of this audit
	 */
	@Override
	public long getAuditId() {
		return model.getAuditId();
	}

	/**
	 * Returns the company ID of this audit.
	 *
	 * @return the company ID of this audit
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the date of this audit.
	 *
	 * @return the date of this audit
	 */
	@Override
	public Date getDate() {
		return model.getDate();
	}

	/**
	 * Returns the description of this audit.
	 *
	 * @return the description of this audit
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the group ID of this audit.
	 *
	 * @return the group ID of this audit
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the primary key of this audit.
	 *
	 * @return the primary key of this audit
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this audit.
	 *
	 * @return the user ID of this audit
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this audit.
	 *
	 * @return the user uuid of this audit
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the action of this audit.
	 *
	 * @param action the action of this audit
	 */
	@Override
	public void setAction(String action) {
		model.setAction(action);
	}

	/**
	 * Sets the audit ID of this audit.
	 *
	 * @param auditId the audit ID of this audit
	 */
	@Override
	public void setAuditId(long auditId) {
		model.setAuditId(auditId);
	}

	/**
	 * Sets the company ID of this audit.
	 *
	 * @param companyId the company ID of this audit
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the date of this audit.
	 *
	 * @param date the date of this audit
	 */
	@Override
	public void setDate(Date date) {
		model.setDate(date);
	}

	/**
	 * Sets the description of this audit.
	 *
	 * @param description the description of this audit
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the group ID of this audit.
	 *
	 * @param groupId the group ID of this audit
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the primary key of this audit.
	 *
	 * @param primaryKey the primary key of this audit
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this audit.
	 *
	 * @param userId the user ID of this audit
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this audit.
	 *
	 * @param userUuid the user uuid of this audit
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected AuditWrapper wrap(Audit audit) {
		return new AuditWrapper(audit);
	}

}