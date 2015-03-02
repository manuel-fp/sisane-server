/*
 * Copyright (C) 2014 al037805
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.daw.bean.generic.specific.implementation;

import net.daw.bean.generic.implementation.BeanGenImpl;
import net.daw.bean.publicinterface.BeanInterface;
import com.google.gson.annotations.Expose;
import java.util.Date;
import net.daw.helper.annotations.FieldMetaInformation;
import net.daw.helper.annotations.SourceMetaInformation;

/**
 *
 * @author al037805
 */
@SourceMetaInformation(
        TableName = "actividad"
)
public class ActividadBeanGenSpImpl extends BeanGenImpl implements BeanInterface {

    public ActividadBeanGenSpImpl() {
    }

    public ActividadBeanGenSpImpl(Integer id) {
        super(id);
    }

    @FieldMetaInformation(
            ShortName = "Enunciado"
    )
    @Expose
    private String enunciado = "";
    //private String presentacion = "";

    @FieldMetaInformation(
            ShortName = "Fecha"
    )
    @Expose
    private Date fecha = new Date();

    @FieldMetaInformation(
            ShortName = "Evaluación"
    )
    @Expose
    private Integer evaluacion = 0;

    @FieldMetaInformation(
            ShortName = "Activo"
    )
    @Expose
    private byte activo = 0;


    public String getEnunciado() {
        return enunciado;
    }


    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }


    public Date getFecha() {
        return fecha;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Integer getEvaluacion() {
        return evaluacion;
    }


    public void setEvaluacion(Integer evaluacion) {
        this.evaluacion = evaluacion;
    }


    public byte getActivo() {
        return activo;
    }


    public void setActivo(byte activo) {
        this.activo = activo;
    }

}
