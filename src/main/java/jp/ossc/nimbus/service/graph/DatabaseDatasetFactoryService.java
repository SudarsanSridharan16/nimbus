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
package jp.ossc.nimbus.service.graph;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.general.Dataset;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.service.connection.ConnectionFactory;
import jp.ossc.nimbus.service.connection.ConnectionFactoryException;

/**
 * �f�[�^�x�[�X�f�[�^�Z�b�g�t�@�N�g���T�[�r�X�B<p>
 *
 * @author k2-taniguchi
 */
public abstract class DatabaseDatasetFactoryService
    extends ServiceBase
    implements DatabaseDatasetFactoryServiceMBean, DatasetFactory, java.io.Serializable {
    
    private static final long serialVersionUID = -1040225706936424053L;
    
    // �萔
    /** �f�t�H���g�t�F�b�`�T�C�Y */
    public static final int DEFAULT_FETCH_SIZE = 10000;
    /** �Z�p���[�^ [=] */
    private static final String SEPARATOR = "=";

    /** �f�[�^�Z�b�g�� */
    private String name;
    /** �R�l�N�V�����t�@�N�g�� */
    private ConnectionFactory connFactory;
    /** [�V���[�Y��=SQL]�̕�����z�� */
    private String[] sqls;
    /** �f�[�^�Z�b�g�����̃��X�g */
    private List dsConditionList;
    /** �L�[�ɃV���[�Y���A�l��SQL�̃}�b�v */
    private Map seriesSqlMap;
    /** �t�F�b�`�T�C�Y */
    private int fetchSize = DEFAULT_FETCH_SIZE;

    // ServiceBase��JavaDoc
    public void createService() throws Exception {
        dsConditionList = new ArrayList();
        seriesSqlMap = new LinkedHashMap();
    }

    // ServiceBase��JavaDoc
    public void startService() throws Exception {
        if (name == null || name.length() == 0) {
            // �T�[�r�X��`�Őݒ肳��Ȃ������ꍇ
            name = getServiceName();
        }

        if (connFactory == null) {
            throw new IllegalArgumentException(
                "ConnectionFactory is null."
            );
        }

        if (sqls == null || sqls.length == 0) {
            throw new IllegalArgumentException(
                "sqls must be specified."
            );
        }

        for (int i = 0; i < sqls.length; i++) {
            String seriesSql = sqls[i];

            int index = seriesSql.indexOf(SEPARATOR);
            if (index == -1) {
                throw new IllegalArgumentException("sqls is invalid." + seriesSql);
            }

            String seriesName = seriesSql.substring(0, index);
            String sql = seriesSql.substring(index + 1);
            // �L�[�ɃV���[�Y��, �l��SQL
            seriesSqlMap.put(seriesName, sql);
        }
    }

    // ServiceBase��JavaDoc
    public void stopService() throws Exception {
        dsConditionList.clear();
        seriesSqlMap.clear();
    }

    // ServiceBase��JavaDoc
    public void destroyService() throws Exception {
        dsConditionList = null;
        seriesSqlMap = null;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public void setName(String name) {
        this.name = name;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public String getName() {
        return this.name;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public void setConnectionFactory(ConnectionFactory connFactory) {
        this.connFactory = connFactory;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public ConnectionFactory getConnectionFactory() {
        return connFactory;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public void setSqls(String[] sqls) {
        this.sqls = sqls;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public String[] getSqls() {
        return sqls;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public void setFetchSize(int size) {
        fetchSize = size;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public int getFetchSize() {
        return fetchSize;
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public void addDatasetCondition(DatasetCondition dsCondition) {
        dsConditionList.add(dsCondition);
    }

    // DatabaseDatasetFactoryServiceMBean��JavaDoc
    public DatasetCondition[] getDatasetConditions() {
        return (DatasetCondition[]) dsConditionList.toArray(
                    new DatasetCondition[dsConditionList.size()]
                );
    }

    // DatasetFactory��JavaDoc
    public Dataset createDataset(DatasetCondition[] dsConditions)
        throws DatasetCreateException {

        // �R�l�N�V�������擾
        Connection conn = null;
        try {
            conn = connFactory.getConnection();
        } catch (ConnectionFactoryException e) {
            // �R�l�N�V�����擾���s
            throw new DatasetCreateException("Dataset [" + name + "]", e);
        }

        DatasetCondition[] conditions = null;
        if (dsConditions != null && dsConditions.length > 0) {
            // �����̃f�[�^�Z�b�g������ݒ�
            conditions = dsConditions;
        }
        if (conditions == null && dsConditionList.size() > 0) {
            // �T�[�r�X��`�Őݒ肳�ꂽ�f�[�^�Z�b�g������ݒ�
            conditions =
                (DatasetCondition[]) dsConditionList.toArray(new DatasetCondition[dsConditionList.size()]);
        }

        Dataset dataset = null;
        // �L�[�ɃV���[�Y���A�l��ResultSet
        Map seriesRsMap = new LinkedHashMap();

        // ���ׂĂ�PreparedStatement�ɓK�p����f�[�^�Z�b�g����
        List allConditions = new ArrayList();
        // �V���[�Y���Ƀ}�b�s���O���ꂽ�f�[�^�Z�b�g����
        Map conditionMap = new HashMap();

        if (conditions != null && conditions.length > 0) {
            // �����Ɠ����f�[�^�Z�b�g���̃f�[�^�Z�b�g����������
            for (int i = 0; i < conditions.length; i++) {
                DatasetCondition dsCondition = conditions[i];

                if (dsCondition instanceof DatabaseDatasetCondition
                    && name.equals(dsCondition.getName())
                ) {
                    String seriesName = conditions[i].getSeriesName();
                    if (seriesName == null) {
                        /*
                         * �V���[�Y�����Ȃ��f�[�^�Z�b�g������
                         * ���ׂĂɓK�p����f�[�^�Z�b�g����
                         */
                        allConditions.add((DatabaseDatasetCondition) dsCondition);
                    } else {
                        if (conditionMap.containsKey(seriesName)) {
                            List list = (List) conditionMap.get(seriesName);
                            list.add(dsCondition);
                        } else {
                            List list = new ArrayList();
                            list.add(dsCondition);
                            // �L�[�ɃV���[�Y���A�l�Ƀf�[�^�Z�b�g�����̃��X�g
                            conditionMap.put(seriesName, list);
                        }
                    }
                }
            }
        }

        try {
            Iterator itr = seriesSqlMap.keySet().iterator();
            while (itr.hasNext()) {
                // �V���[�Y
                String series = (String) itr.next();
                PreparedStatement pstmt =
                    conn.prepareStatement(
                        (String) seriesSqlMap.get(series),
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY 
                    );
                
                pstmt.setFetchSize(fetchSize);
                pstmt.setFetchDirection(ResultSet.FETCH_FORWARD);

                if (allConditions.size() > 0) {
                    /*
                     * �V���[�Y���Ȃ��̃f�[�^�Z�b�g������
                     * ���ׂĂ�PreparedStatement�ɓK�p
                     */
                    for (int i = 0; i < allConditions.size(); i++) {
                        DatabaseDatasetCondition condition = (DatabaseDatasetCondition) allConditions.get(i);
                        setObject(pstmt, condition);
                    }
                } else if (conditionMap.containsKey(series)) {
                    // �e�V���[�Y�p�̃f�[�^�Z�b�g������PreparedStatement�ɓK�p
                    List list = (List) conditionMap.get(series);
                    for (int i = 0; i < list.size(); i++) {
                        DatabaseDatasetCondition condition =(DatabaseDatasetCondition) list.get(i);
                        setObject(pstmt, condition);
                    }
                }
                // SQL���s
                ResultSet rs = pstmt.executeQuery();
                seriesRsMap.put(series, rs);
            }

            // �V���[�Y���̔z��
            String[] series = null;
            // �������ʂ̔z��
            ResultSet[] rSets = null;

            if (seriesRsMap.size() > 0) {
                series =
                    (String[]) seriesRsMap.keySet().toArray(new String[seriesRsMap.size()]);
                rSets =
                    (ResultSet[]) seriesRsMap.values().toArray(new ResultSet[seriesRsMap.size()]);
            }

            // �f�[�^�Z�b�g�����
            dataset = createDataset(dsConditions, series, rSets);
        } catch (SQLException e) {
            // �f�[�^�x�[�X�֘A
            throw new DatasetCreateException("Dataset [" + name + "]", e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return dataset;
    }

    /**
     * PreparedStatement�Ɏw�肳�ꂽ�f�[�^�x�[�X��������l��ݒ肷��B<p>
     *
     * @param pstmt PreparedStatement
     * @param dbDsCondition �f�[�^�x�[�X�f�[�^�Z�b�g����
     * @exception DatasetCreateException
     * @exception SQLException
     */
    private void setObject(
        PreparedStatement pstmt,
        DatabaseDatasetCondition dbDsCondition
    ) throws DatasetCreateException, SQLException {
        // �p�����[�^���^�f�[�^
        ParameterMetaData paramMetaData = pstmt.getParameterMetaData();
        if (paramMetaData == null) {
            throw new DatasetCreateException(
                "ParameterMetaData is null."
            );
        }

        // �p�����[�^�J�E���g
        int paramCnt = paramMetaData.getParameterCount();

        // �l��PreparedStatement�ɐݒ�
        if (paramCnt > 0) {
            for (int k = 0; k < paramCnt; k++) {
                Object paramObj = dbDsCondition.getParamObject(k);
                if (paramObj != null) {
                    pstmt.setObject(k + 1, paramObj);
                }
            }
        }
    }

    /**
     * �f�[�^�Z�b�g���쐬����B<p>
     *
     * @param dsConditions �f�[�^�Z�b�g����
     * @param seriesArray �V���[�Y���̔z��
     * @param rSets ResultSet�̔z��
     * @return �f�[�^�Z�b�g
     * @exception DatasetCreateException
     */
    abstract protected Dataset createDataset(
        DatasetCondition[] dsConditions,
        String[] seriesArray,
        ResultSet[] rSets
    ) throws DatasetCreateException;

}
