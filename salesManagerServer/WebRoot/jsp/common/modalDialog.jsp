<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- Modal Url -->
<div class="modal fade bs-example-modal-lg" id="modalRemote" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-info-sign"></span>&ensp;消息查看</h4>
      </div>
      <div class="modal-body" id="modalRemote_body" >
        <div class="progress">
		  <div class="progress-bar progress-bar-striped active"  role="progressbar" 
		  	aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
		    <span class="sr-only">45% Complete</span>
		  </div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div>
  </div>
</div>