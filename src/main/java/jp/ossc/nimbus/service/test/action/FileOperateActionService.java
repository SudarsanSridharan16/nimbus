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
package jp.ossc.nimbus.service.test.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import jp.ossc.nimbus.core.ServiceBase;
import jp.ossc.nimbus.core.Utility;
import jp.ossc.nimbus.io.RecurciveSearchFile;
import jp.ossc.nimbus.service.test.TestContext;
import jp.ossc.nimbus.service.test.TestAction;
import jp.ossc.nimbus.service.test.TestActionEstimation;

/**
 * �t�@�C��������s���e�X�g�A�N�V�����B<p>
 * ����̏ڍׂ́A{@link #execute(TestContext, String, Reader)}���Q�ƁB<br>
 * 
 * @author M.Takata
 */
public class FileOperateActionService extends ServiceBase implements TestAction,TestActionEstimation, FileOperateActionServiceMBean{
    
    private static final long serialVersionUID = -7746000195947141887L;
    protected double expectedCost = 0d;

    /**
     * ���\�[�X�̓��e��ǂݍ���ŁA�t�@�C��������s���B<p>
     * ���\�[�X�̃t�H�[�}�b�g�́A�ȉ��B<br>
     * <pre>
     * operateType
     * filePaths
     * </pre>
     * operateType�́AMOVE�ACOPY�ADELETE�ACLEAR�ALS�̂����ꂩ���w�肷��BMOVE�́A�t�@�C���ړ��BCOPY�̓t�@�C���R�s�[�BDELETE�́A�t�@�C���폜�BCLEAR�́A�t�@�C���̒��g����ɂ���BLS�́A�w�肳�ꂽ�p�X�̃t�@�C�����X�g���擾����B<br>
     * filePaths�́AoperateType�ɂ���āA�L�q���@���قȂ�B�܂��AfilePaths�́A�����s�w��\�ł���B<br>
     * <ul>
     * <li>operateType��"MOVE"�̏ꍇ<br>�ړ����t�@�C���p�X�ƈړ���f�B���N�g���p�X��2�s���w�肷��B�ړ���f�B���N�g���p�X���w�肳��Ă��Ȃ��ꍇ�́A{@link TestContext#getCurrentDirectory()}�ɓ����t�@�C�����ňړ�����B</li>
     * <li>operateType��"COPY"�̏ꍇ<br>�R�s�[���t�@�C���p�X�ƃR�s�[��t�@�C���p�X��2�s���w�肷��B�R�s�[��t�@�C���p�X���w�肳��Ă��Ȃ��ꍇ�́A{@link TestContext#getCurrentDirectory()}�ɓ����t�@�C�����ŃR�s�[����B</li>
     * <li>operateType��"DELETE"�̏ꍇ<br>�폜�t�@�C���p�X���w�肷��B</li>
     * <li>operateType��"CLEAR"�̏ꍇ<br>�Ώۃt�@�C���p�X���w�肷��B</li>
     * <li>operateType��"LS"�̏ꍇ<br>�Ώۃt�@�C���p�X���w�肷��B</li>
     * </ul>
     *
     * @param context �R���e�L�X�g
     * @param actionId �A�N�V����ID
     * @param resource ���\�[�X
     * @return LS�̏ꍇ�̂݌��������t�@�C���̃��X�g�B����ȊO�́Anull
     */
    public Object execute(TestContext context, String actionId, Reader resource) throws Exception{
        BufferedReader br = new BufferedReader(resource);
        Object result = null;
        try{
            String operateType = br.readLine();
            if("MOVE".equals(operateType)){
                String srcPath = br.readLine();
                if(srcPath == null || srcPath.length() == 0){
                    throw new Exception("Unexpected EOF on srcPath");
                }
                do{
                    List srcFiles = toFiles(srcPath);
                    if(srcFiles != null){
                        String dstPath = br.readLine();
                        File dstDir = null;
                        if(dstPath == null || dstPath.length() == 0){
                            dstDir = context.getCurrentDirectory();
                        }else{
                            dstDir = new File(replaceProperty(dstPath));
                            if(!dstDir.isAbsolute()){
                                dstDir = new File(context.getCurrentDirectory(), dstDir.getPath());
                            }
                        }
                        if(dstDir.exists()){
                            if(dstDir.isDirectory()){
                                move(srcFiles, dstDir);
                            }else{
                                throw new Exception("Not directory. path=" + dstDir.getPath());
                            }
                        }else{
                            if(dstDir.mkdirs()){
                                move(srcFiles, dstDir);
                            }else{
                                throw new Exception("Can' not create directory. path=" + dstDir.getPath());
                            }
                        }
                    }
                }while((srcPath = br.readLine()) != null && srcPath.length() != 0);
            }else if("COPY".equals(operateType)){
                String srcPath = br.readLine();
                if(srcPath == null || srcPath.length() == 0){
                    throw new Exception("Unexpected EOF on srcPath");
                }
                do{
                    List srcFiles = toFiles(srcPath);
                    if(srcFiles != null){
                        String dstPath = br.readLine();
                        File dstDir = null;
                        if(dstPath == null || dstPath.length() == 0){
                            dstDir = context.getCurrentDirectory();
                        }else{
                            dstDir = new File(replaceProperty(dstPath));
                            if(!dstDir.isAbsolute()){
                                dstDir = new File(context.getCurrentDirectory(), dstDir.getPath());
                            }
                        }
                        if(dstDir.exists()){
                            if(dstDir.isDirectory()){
                                copy(srcFiles, dstDir);
                            }else{
                                throw new Exception("Not directory. path=" + dstDir.getPath());
                            }
                        }else{
                            if(dstDir.mkdirs()){
                                copy(srcFiles, dstDir);
                            }else{
                                throw new Exception("Can' not create directory. path=" + dstDir.getPath());
                            }
                        }
                    }
                }while((srcPath = br.readLine()) != null && srcPath.length() != 0);
            }else if("DELETE".equals(operateType)){
                String filePath = br.readLine();
                if(filePath == null || filePath.length() == 0){
                    throw new Exception("Unexpected EOF on filePath");
                }
                do{
                    List files = toFiles(filePath);
                    if(files != null){
                        delete(files);
                    }
                }while((filePath = br.readLine()) != null && filePath.length() != 0);
            }else if("CLEAR".equals(operateType)){
                String filePath = br.readLine();
                if(filePath == null || filePath.length() == 0){
                    throw new Exception("Unexpected EOF on filePath");
                }
                do{
                    List files = toFiles(filePath);
                    if(files != null){
                        clear(files);
                    }
                }while((filePath = br.readLine()) != null && filePath.length() != 0);
            }else if("LS".equals(operateType)){
                String filePath = br.readLine();
                if(filePath == null || filePath.length() == 0){
                    throw new Exception("Unexpected EOF on filePath");
                }
                List fileList = null;
                do{
                    List files = toFiles(filePath);
                    if(files != null){
                        fileList = ls(files, fileList);
                    }
                }while((filePath = br.readLine()) != null && filePath.length() != 0);
                result = fileList;
            }else{
                throw new Exception("Illegal operateType : " + operateType);
            }
        }finally{
            br.close();
            br = null;
        }
        return result;
    }
    
    protected void move(List files, File dir) throws IOException{
        for(int i = 0; i < files.size(); i++){
            File file = (File)files.get(i);
            if(file.isDirectory()){
                File destDir = new File(dir, file.getName());
                if(!destDir.exists()){
                    destDir.mkdirs();
                }
                RecurciveSearchFile.copyAllTree(file, destDir);
                RecurciveSearchFile.deleteAllTree(file);
            }else{
                RecurciveSearchFile.dataCopy(file, new File(dir, file.getName()));
                file.delete();
            }
        }
    }
    
    protected void copy(List files, File dir) throws IOException{
        for(int i = 0; i < files.size(); i++){
            File file = (File)files.get(i);
            if(file.isDirectory()){
                File destDir = new File(dir, file.getName());
                if(!destDir.exists()){
                    destDir.mkdirs();
                }
                RecurciveSearchFile.copyAllTree(file, destDir);
            }else{
                RecurciveSearchFile.dataCopy(file, new File(dir, file.getName()));
            }
        }
    }
    
    protected void delete(List files) throws IOException{
        for(int i = 0; i < files.size(); i++){
            File file = (File)files.get(i);
            if(file.isDirectory()){
                if(!RecurciveSearchFile.deleteAllTree(file)){
                    throw new IOException("Can not delete files. path=" + file);
                }
            }else{
                if(!file.delete()){
                    throw new IOException("Can not delete files. path=" + file);
                }
            }
        }
    }
    
    protected void clear(List files) throws IOException{
        for(int i = 0; i < files.size(); i++){
            File file = (File)files.get(i);
            if(!file.isDirectory()){
                FileOutputStream fos = new FileOutputStream(file);
                fos.close();
            }
        }
    }
    
    protected List ls(List files, List fileList) throws IOException{
        for(int i = 0; i < files.size(); i++){
            File file = (File)files.get(i);
            if(file.exists()){
                if(fileList == null){
                    fileList = new ArrayList();
                }
                if(file.isDirectory()){
                    File[] childlen = file.listFiles();
                    for(int j = 0; j < childlen.length; j++){
                        fileList.add(childlen[j]);
                    }
                }else{
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }
    
    protected List toFiles(String path) throws IOException{
        List result = null;
        path = replaceProperty(path);
        File file = new File(path);
        if(file.exists()){
            result = new ArrayList();
            result.add(file);
        }else{
            File parentFile = file;
            while((parentFile = parentFile.getParentFile()) != null && !parentFile.exists());
            
            if(parentFile == null){
                parentFile = new File(".");
            }else{
                path = path.substring(parentFile.getPath().length() + 1);
            }
            try{
                RecurciveSearchFile rsf = new RecurciveSearchFile(parentFile);
                File[] files = rsf.listAllTreeFiles(path, RecurciveSearchFile.SEARCH_TYPE_ALL);
                if(files != null && files.length != 0){
                    result = new ArrayList();
                    for(int i = 0; i < files.length; i++){
                        result.add(files[i]);
                    }
                }
            }catch(PatternSyntaxException e){
            }
        }
        return result;
    }
    
    protected String replaceProperty(String textValue){
        
        // �V�X�e���v���p�e�B�̒u��
        textValue = Utility.replaceSystemProperty(textValue);
        
        // �T�[�r�X���[�_�\���v���p�e�B�̒u��
        if(getServiceLoader() != null){
            textValue = Utility.replaceServiceLoderConfig(
                textValue,
                getServiceLoader().getConfig()
            );
        }
        
        // �}�l�[�W���v���p�e�B�̒u��
        if(getServiceManager() != null){
            textValue = Utility.replaceManagerProperty(
                getServiceManager(),
                textValue
            );
        }
        
        // �T�[�o�v���p�e�B�̒u��
        textValue = Utility.replaceServerProperty(textValue);
        
        return textValue;
    }
    
    public void setExpectedCost(double cost) {
        expectedCost = cost;
    }
    
    public double getExpectedCost() {
        return expectedCost;
    }
}
