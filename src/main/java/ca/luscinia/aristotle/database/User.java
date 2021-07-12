/*
 * Aristotle Learning Platform: Luscinia Enterprises Assn.
 * Copyright (C) 2020
 *     1261612 B.C. LTD.
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
import ca.luscinia.aristotle.database.user.PrivacySettings;
import ca.luscinia.aristotle.database.user.SecurityQuestion;
import ca.luscinia.aristotle.database.user.Status;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Document("users")
public class User {
    @Id
    private ObjectId id;

    private String preferredName;
    private String firstName;
    private String middleNames;
    private String lastName;

    private String school;

    private ArrayList<String> courses;
    private ArrayList<String> teachers;
    private ArrayList<String> parents;
    private ArrayList<String> students;

    private Date accountCreated;

    private Date dateOfBirth;

    private int classicGradeLevel;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String phone;
    private String password;
    private ArrayList<String> roles;

    private boolean enabled;
    private Status status;
    private PrivacySettings privacySettings;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String passwordResetKey;
    private Date passwordChange;
    private ArrayList<SecurityQuestion> securityQuestions;
    private ArrayList<LoginRecord> loginRecords;
    private boolean use2FA;
    private String method2FA;
    private String secret2FA;

    private LearningStyle learningStyle;

    // Azure Cosmos DB Shard Key
    private UUID uuid;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public ArrayList<String> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<String> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<String> getParents() {
        return parents;
    }

    public void setParents(ArrayList<String> parents) {
        this.parents = parents;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<String> students) {
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

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
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

    public boolean isUse2FA() {
        return use2FA;
    }

    public void setUse2FA(boolean use2FA) {
        this.use2FA = use2FA;
    }

    public String getMethod2FA() {
        return method2FA;
    }

    public void setMethod2FA(String method2FA) {
        this.method2FA = method2FA;
    }

    public String getSecret2FA() {
        return secret2FA;
    }

    public void setSecret2FA(String secret2FA) {
        this.secret2FA = secret2FA;
    }

    public LearningStyle getLearningStyle() {
        return learningStyle;
    }

    public void setLearningStyle(LearningStyle learningStyle) {
        this.learningStyle = learningStyle;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", preferredName='" + preferredName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleNames='" + middleNames + '\'' +
                ", lastName='" + lastName + '\'' +
                ", school='" + school + '\'' +
                ", courses=" + courses +
                ", teachers=" + teachers +
                ", parents=" + parents +
                ", students=" + students +
                ", accountCreated=" + accountCreated +
                ", dateOfBirth=" + dateOfBirth +
                ", classicGradeLevel=" + classicGradeLevel +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", enabled=" + enabled +
                ", status=" + status +
                ", passwordResetKey='" + passwordResetKey + '\'' +
                ", passwordChange=" + passwordChange +
                ", securityQuestions=" + securityQuestions +
                ", loginRecords=" + loginRecords +
                ", use2FA=" + use2FA +
                ", method2FA='" + method2FA + '\'' +
                ", secret2FA='" + secret2FA + '\'' +
                ", learningStyle=" + learningStyle +
                '}';
    }
}
