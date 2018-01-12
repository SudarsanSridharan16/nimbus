/*
 * This software is distributed under following license based on modified BSD
 * style license.
 * ----------------------------------------------------------------------
 * 
 * Copyright 2009 The Nimbus2 Project. All rights reserved.
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
 * policies, either expressed or implied, of the Nimbus2 Project.
 */
package jp.ossc.nimbus.service.rest;

/**
 * REST�T�[�o�B<p>
 * REST���N�G�X�g���������āAREST���X�|���X�ɏ������ʂ��i�[����B<br>
 *
 * @author M.Takata
 */
public interface RestServer{
    
    /**
     * POST���\�b�h��REST���N�G�X�g�̏������s���B<p>
     *
     * @param request REST���N�G�X�g
     * @param response REST���X�|���X
     * @exception Throwable �������ɗ�O�����������ꍇ
     */
    public void processPost(PostRestRequest request, PostRestResponse response) throws Throwable;
    
    /**
     * GET���\�b�h��REST���N�G�X�g�̏������s���B<p>
     *
     * @param request REST���N�G�X�g
     * @param response REST���X�|���X
     * @exception Throwable �������ɗ�O�����������ꍇ
     */
    public void processGet(GetRestRequest request, GetRestResponse response) throws Throwable;
    
    /**
     * HEAD���\�b�h��REST���N�G�X�g�̏������s���B<p>
     *
     * @param request REST���N�G�X�g
     * @param response REST���X�|���X
     * @exception Throwable �������ɗ�O�����������ꍇ
     */
    public void processHead(HeadRestRequest request, HeadRestResponse response) throws Throwable;
    
    /**
     * PUT���\�b�h��REST���N�G�X�g�̏������s���B<p>
     *
     * @param request REST���N�G�X�g
     * @param response REST���X�|���X
     * @exception Throwable �������ɗ�O�����������ꍇ
     */
    public void processPut(PutRestRequest request, PutRestResponse response) throws Throwable;
    
    /**
     * DELETE���\�b�h��REST���N�G�X�g�̏������s���B<p>
     *
     * @param request REST���N�G�X�g
     * @param response REST���X�|���X
     * @exception Throwable �������ɗ�O�����������ꍇ
     */
    public void processDelete(DeleteRestRequest request, DeleteRestResponse response) throws Throwable;
    
    /**
     * OPTIONS���\�b�h��REST���N�G�X�g�̏������s���B<p>
     *
     * @param request REST���N�G�X�g
     * @param response REST���X�|���X
     * @exception Throwable �������ɗ�O�����������ꍇ
     */
    public void processOptions(OptionsRestRequest request, OptionsRestResponse response) throws Throwable;
}