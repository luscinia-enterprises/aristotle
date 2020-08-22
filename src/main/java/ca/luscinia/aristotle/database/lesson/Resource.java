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

package ca.luscinia.aristotle.database.lesson;

import ca.luscinia.aristotle.database.general.LearningStyle;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("resource")
public class Resource {

    private String resourceId;

    private LearningStyle learningStyleDelta;

    private int performance;
    private int response;

    private int timesServed;
    private int servedScore;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public LearningStyle getLearningStyleDelta() {
        return learningStyleDelta;
    }

    public void setLearningStyleDelta(LearningStyle learningStyleDelta) {
        this.learningStyleDelta = learningStyleDelta;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public int getTimesServed() {
        return timesServed;
    }

    public void setTimesServed(int timesServed) {
        this.timesServed = timesServed;
    }

    public int getServedScore() {
        return servedScore;
    }

    public void setServedScore(int servedScore) {
        this.servedScore = servedScore;
    }
}
