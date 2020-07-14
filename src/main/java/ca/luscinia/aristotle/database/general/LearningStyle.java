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

package ca.luscinia.aristotle.database.general;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("learningStyle")
public class LearningStyle {

    private int llss;
    private int slss;
    private int alss;
    private int klss;
    private int mlss;
    private int sms;
    private int qms;
    private int tms;

    public int getLlss() {
        return llss;
    }

    public void setLlss(int llss) {
        this.llss = llss;
    }

    public int getSlss() {
        return slss;
    }

    public void setSlss(int slss) {
        this.slss = slss;
    }

    public int getAlss() {
        return alss;
    }

    public void setAlss(int alss) {
        this.alss = alss;
    }

    public int getKlss() {
        return klss;
    }

    public void setKlss(int klss) {
        this.klss = klss;
    }

    public int getMlss() {
        return mlss;
    }

    public void setMlss(int mlss) {
        this.mlss = mlss;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getQms() {
        return qms;
    }

    public void setQms(int qms) {
        this.qms = qms;
    }

    public int getTms() {
        return tms;
    }

    public void setTms(int tms) {
        this.tms = tms;
    }
}
