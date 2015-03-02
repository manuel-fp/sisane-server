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
package net.daw.service.specific.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.daw.bean.generic.specific.implementation.TipopropuestaBeanGenSpImpl;
import net.daw.dao.specific.implementation.TipopropuestaDaoSpcImpl;
import net.daw.helper.statics.EncodingUtilHelper;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.service.publicinterface.MetaServiceInterface;
import net.daw.service.publicinterface.TableServiceInterface;
import net.daw.service.publicinterface.ViewServiceInterface;

/**
 *
 * @author al037805
 */
public class TipopropuestaServiceSpImpl implements TableServiceInterface, ViewServiceInterface, MetaServiceInterface {
    
    protected Connection oConnection = null;
    protected String strObjectName = null;
    protected String strPojo = null; 

    public TipopropuestaServiceSpImpl(String strObject, String pojo, Connection con) {
        strObjectName = strObject;
        oConnection = con;
        strPojo = Character.toUpperCase(pojo.charAt(0)) + pojo.substring(1);
    }
    
    @Override
    public void setsource(String source) throws Exception {
        strObjectName = source;
    }
    
    @Override
    public void setpojo(String pojo) throws Exception {
        strPojo = Character.toUpperCase(pojo.charAt(0)) + pojo.substring(1);
    }

    @Override
    public String remove(Integer id) throws Exception {
        String resultado = null;
        try {
            oConnection.setAutoCommit(false);
            TipopropuestaDaoSpcImpl oTipopropuestaDAO = new TipopropuestaDaoSpcImpl(strObjectName,"tipopropuesta" , oConnection);
            TipopropuestaBeanGenSpImpl oTipopropuesta = new TipopropuestaBeanGenSpImpl(id);
            Map<String, String> data = new HashMap<>();
            oTipopropuestaDAO.remove(oTipopropuesta);
            data.put("status", "200");
            data.put("message", "se ha eliminado el registro con id=" + oTipopropuesta.getId());
            Gson gson = new Gson();
            resultado = gson.toJson(data);
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
        }
        return resultado;
    }

    @Override
    public String set(String jason) throws Exception {
        String resultado = null;
        try {
            oConnection.setAutoCommit(false);
            TipopropuestaDaoSpcImpl oTipopropuestaDAO = new TipopropuestaDaoSpcImpl(strObjectName,"tipopropuesta" , oConnection);
            TipopropuestaBeanGenSpImpl oTipopropuesta = new TipopropuestaBeanGenSpImpl();
            Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
            jason = EncodingUtilHelper.decodeURIComponent(jason);
            oTipopropuesta = gson.fromJson(jason, oTipopropuesta.getClass());
            oTipopropuesta = oTipopropuestaDAO.set(oTipopropuesta);
            Map<String, String> data = new HashMap<>();
            data.put("status", "200");
            data.put("message", Integer.toString(oTipopropuesta.getId()));
            resultado = gson.toJson(data);
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return resultado;
    }

    @Override
    public String get(Integer id) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            TipopropuestaDaoSpcImpl oTipopropuestaDAO = new TipopropuestaDaoSpcImpl(strObjectName,"tipopropuesta" , oConnection);
            TipopropuestaBeanGenSpImpl oTipopropuesta = new TipopropuestaBeanGenSpImpl(id);
            oTipopropuesta = oTipopropuestaDAO.get(oTipopropuesta, 1);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd/MM/yyyy");
            Gson gson = gsonBuilder.create();
            data = gson.toJson(oTipopropuesta);
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
        }
        return data;
    }

    @Override
    public String getpage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            TipopropuestaDaoSpcImpl oTipopropuestaDAO = new TipopropuestaDaoSpcImpl(strObjectName,"tipopropuesta" , oConnection);
            List<TipopropuestaBeanGenSpImpl> oTipopropuestas = oTipopropuestaDAO.getPage(intRegsPerPag, intPage, alFilter, hmOrder);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd/MM/yyyy");
            Gson gson = gsonBuilder.create();
            data = gson.toJson(oTipopropuestas);
            data = "{\"list\":" + data + "}";
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getpage ERROR: " + ex.getMessage()));
        }
        return data;
    }

    @Override
    public String getpages(int intRegsPerPag, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            TipopropuestaDaoSpcImpl oTipopropuestaDAO = new TipopropuestaDaoSpcImpl(strObjectName,"tipopropuesta" , oConnection);
            int pages = oTipopropuestaDAO.getPages(intRegsPerPag, alFilter);
            data = "{\"data\":\"" + Integer.toString(pages) + "\"}";
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getpages ERROR: " + ex.getMessage()));
        }
        return data;
    }

    @Override
    public String getcount(ArrayList<FilterBeanHelper> alFilter) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            TipopropuestaDaoSpcImpl oTipopropuestaDAO = new TipopropuestaDaoSpcImpl(strObjectName,"tipopropuesta" , oConnection);
            int registers = oTipopropuestaDAO.getCount(alFilter);
            data = "{\"data\":\"" + Integer.toString(registers) + "\"}";
            oConnection.commit();

        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getcount ERROR: " + ex.getMessage()));
        }
        return data;
    }

    @Override
    public String getprettycolumns() throws Exception {
        String data = null;
        ArrayList<String> alColumns = null;
        try {
            oConnection.setAutoCommit(false);
            TipopropuestaDaoSpcImpl oTipopropuestaDAO = new TipopropuestaDaoSpcImpl(strObjectName,"tipopropuesta" , oConnection);
            alColumns = oTipopropuestaDAO.getPrettyColumnsNames();
            data = new Gson().toJson(alColumns);
            //data = "{\"data\":" + data + "}";
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getprettycolumns ERROR: " + ex.getMessage()));
        }
        return data;
    }

    @Override
    public String getcolumns() throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            ArrayList<String> alColumns = null;
            TipopropuestaDaoSpcImpl oTipopropuestaDAO = new TipopropuestaDaoSpcImpl(strObjectName,"tipopropuesta" , oConnection);
            alColumns = oTipopropuestaDAO.getColumnsNames();
            data = new Gson().toJson(alColumns);
            //data = "{\"data\":" + data + "}";
            oConnection.commit();

        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getcolumns ERROR: " + ex.getMessage()));
        }
        return data;
    }

    @Override
    public String getaggregateviewone(Integer id) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            String columns = this.getcolumns();
            String prettyColumns = this.getprettycolumns();
            //String types = this.getTypes();
            String one = this.get(id);
            data = "{\"data\":{"
                    + "\"columns\":" + columns
                    + ",\"prettyColumns\":" + prettyColumns
                    // + ",\"types\":" + types
                    + ",\"data\":" + one
                    + "}}";
            oConnection.commit();

        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getaggregateviewone ERROR: " + ex.getMessage()));
        }
        return data;
    }

    @Override
    public String getaggregateviewsome(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            String columns = this.getcolumns();
            String prettyColumns = this.getprettycolumns();
            //String types = this.getTypes();
            String page = this.getpage(intRegsPerPag, intPage, alFilter, hmOrder);
            String pages = this.getpages(intRegsPerPag, alFilter);
            String registers = this.getcount(alFilter);
            data = "{\"data\":{"
                    + "\"columns\":" + columns
                    + ",\"prettyColumns\":" + prettyColumns
                    // + ",\"types\":" + types
                    + ",\"page\":" + page
                    + ",\"pages\":" + pages
                    + ",\"registers\":" + registers
                    + "}}";
            oConnection.commit();

        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getaggregateviewsome ERROR: " + ex.getMessage()));
        }
        return data;
    }

    @Override
    public String updateone(int intId, String strTabla, String strCampo, String strValor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
