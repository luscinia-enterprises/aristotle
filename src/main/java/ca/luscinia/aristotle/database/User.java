/*
 * Aristotle Learning Platform: Luscinia Enterprises Assn.
 * Copyright (C) 2020
 *     Luscinia Enterprises Assn. <development@luscinia.ca>
 *     Varun Patel <vpatel@luscinia.ca>, <varun@varunpatel.ca>
 *     Milan Bumbulovic <mbumbulovic@luscinia.ca>
 *     Jacob Chun <jchun@luscinia.ca>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ca.luscinia.aristotle.database;

import ca.luscinia.aristotle.database.general.LearningStyle;
import ca.luscinia.aristotle.database.user.LoginRecord;
import ca.luscinia.aristotle.database.user.Role;
import ca.luscinia.aristotle.database.user.SecurityQuestion;
import ca.luscinia.aristotle.database.user.Status;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Document("users")
public class User {
    @Id
    private ObjectId id;

    private String preferredName;
    private String firstName;
    private String middleNames;
    private String lastName;

    private ArrayList<ObjectId> courses;
    private ArrayList<ObjectId> teachers;
    private ArrayList<ObjectId> parents;
    private ArrayList<ObjectId> students;

    private Date accountCreated;

    // Azure Cosmos DB Shard Key
    private Date dateOfBirth;

    private int classicGradeLevel;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String phone;
    private String password;
    private Set<Role> roles;

    private boolean enabled;
    private Status status;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String passwordResetKey;
    private Date passwordChange;
    private ArrayList<SecurityQuestion> securityQuestions;
    private ArrayList<LoginRecord> loginRecords;

    private LearningStyle learningStyle;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<ObjectId> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<ObjectId> courses) {
        this.courses = courses;
    }

    public ArrayList<ObjectId> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<ObjectId> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<ObjectId> getParents() {
        return parents;
    }

    public void setParents(ArrayList<ObjectId> parents) {
        this.parents = parents;
    }

    public ArrayList<ObjectId> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<ObjectId> students) {
        this.students = students;
    }

    public Date getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(Date accountCreated) {
        this.accountCreated = accountCreated;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getClassicGradeLevel() {
        return classicGradeLevel;
    }

    public void setClassicGradeLevel(int classicGradeLevel) {
        this.classicGradeLevel = classicGradeLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPasswordResetKey() {
        return passwordResetKey;
    }

    public void setPasswordResetKey(String passwordResetKey) {
        this.passwordResetKey = passwordResetKey;
    }

    public Date getPasswordChange() {
        return passwordChange;
    }

    public void setPasswordChange(Date passwordChange) {
        this.passwordChange = passwordChange;
    }

    public ArrayList<SecurityQuestion> getSecurityQuestions() {
        return securityQuestions;
    }

    public void setSecurityQuestions(ArrayList<SecurityQuestion> securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    public ArrayList<LoginRecord> getLoginRecords() {
        return loginRecords;
    }

    public void setLoginRecords(ArrayList<LoginRecord> loginRecords) {
        this.loginRecords = loginRecords;
    }

    public LearningStyle getLearningStyle() {
        return learningStyle;
    }

    public void setLearningStyle(LearningStyle learningStyle) {
        this.learningStyle = learningStyle;
    }
}
