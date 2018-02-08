/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2003 The Nimbus Project. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer. 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE NIMBUS PROJECT ``AS IS'' AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN
 * NO EVENT SHALL THE NIMBUS PROJECT OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of the Nimbus Project.
 */
package jp.ossc.nimbus.util.converter;

/**
 * SQL DATE�^�R���o�[�^�B<p>
 * 
 * @author M.Takata
 */
public class SQLDateConverter implements ReversibleConverter, java.io.Serializable{
    
    private static final long serialVersionUID = -3345638749436216905L;

    /**
     * java.util.Date����java.sql.Date�I�u�W�F�N�g�ɕϊ�����ϊ���ʒ萔�B<p>
     */
    public static final int DATE_TO_SQL_DATE = 1;
    
    /**
     * java.util.Date����java.sql.Time�I�u�W�F�N�g�ɕϊ�����ϊ���ʒ萔�B<p>
     */
    public static final int DATE_TO_SQL_TIME = 2;
    
    /**
     * java.util.Date����java.sql.Timestamp�I�u�W�F�N�g�ɕϊ�����ϊ���ʒ萔�B<p>
     */
    public static final int DATE_TO_SQL_TIMESTAMP = 3;
    
    /**
     * java.util.Date����java.sql.Date�I�u�W�F�N�g�ɕϊ�����ϊ���ʒ萔�B<p>
     */
    public static final int SQL_DATE_TO_DATE = -1;
    
    /**
     * java.util.Date����java.sql.Time�I�u�W�F�N�g�ɕϊ�����ϊ���ʒ萔�B<p>
     */
    public static final int SQL_TIME_TO_DATE = -2;
    
    /**
     * java.util.Date����java.sql.Timestamp�I�u�W�F�N�g�ɕϊ�����ϊ���ʒ萔�B<p>
     */
    public static final int SQL_TIMESTAMP_TO_DATE = -3;
    
    protected int convertType;
    
    /**
     * �ϊ���ʂ�ݒ肷��B<p>
     *
     * @param type �ϊ����
     * @see #DATE_TO_SQL_DATE
     * @see #DATE_TO_SQL_TIME
     * @see #DATE_TO_SQL_TIMESTAMP
     * @see #SQL_DATE_TO_DATE
     * @see #SQL_TIME_TO_DATE
     * @see #SQL_TIMESTAMP_TO_DATE
     */
    public void setConvertType(int type){
        convertType = type;
    }
    
    /**
     * �ϊ���ʂ��擾����B<p>
     *
     * @return �ϊ����
     */
    public int getConvertType(){
        return convertType;
    }
    
    public Object convert(Object obj) throws ConvertException{
        if(obj == null){
            return null;
        }
        java.util.Date fromDate = null;
        switch(convertType){
        case DATE_TO_SQL_DATE:
            fromDate = (java.util.Date)obj;
            return new java.sql.Date(fromDate.getTime());
        case DATE_TO_SQL_TIME:
            fromDate = (java.util.Date)obj;
            return new java.sql.Time(fromDate.getTime());
        case DATE_TO_SQL_TIMESTAMP:
            fromDate = (java.util.Date)obj;
            return new java.sql.Timestamp(fromDate.getTime());
        case SQL_DATE_TO_DATE:
            java.sql.Date fromSQLDate = (java.sql.Date)obj;
            return new java.util.Date(fromSQLDate.getTime());
        case SQL_TIME_TO_DATE:
            java.sql.Time fromSQLTime = (java.sql.Time)obj;
            return new java.util.Date(fromSQLTime.getTime());
        case SQL_TIMESTAMP_TO_DATE:
            java.sql.Timestamp fromSQLTimestamp = (java.sql.Timestamp)obj;
            return new java.util.Date(fromSQLTimestamp.getTime());
        default:
            throw new ConvertException("Unknown convert type : " + convertType);
        }
    }
}