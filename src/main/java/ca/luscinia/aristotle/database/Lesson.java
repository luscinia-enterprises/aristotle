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

import ca.luscinia.aristotle.database.lesson.Resource;
import ca.luscinia.aristotle.database.lesson.StudentPerformance;
import ca.luscinia.aristotle.database.resource.Tags;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("lessons")
public class Lesson {
    @Id
    private ObjectId id;

    // Azure Cosmos DB Shard Key
    private ObjectId courseid;

    private ArrayList<Resource> resources;

    private ArrayList<String> students;

    private ArrayList<StudentPerformance> performance;

    private Tags tags;

    private int overallPerformance;

    private int overallResponse;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }

    public ArrayList<StudentPerformance> getPerformance() {
        return performance;
    }

    public void setPerformance(ArrayList<StudentPerformance> performance) {
        this.performance = performance;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public int getOverallPerformance() {
        return overallPerformance;
    }

    public void setOverallPerformance(int overallPerformance) {
        this.overallPerformance = overallPerformance;
    }

    public int getOverallResponse() {
        return overallResponse;
    }

    public void setOverallResponse(int overallResponse) {
        this.overallResponse = overallResponse;
    }
}
