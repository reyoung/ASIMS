require(["admin/active","field_checker"],function(act,fc){
    act("nav_news_create")
    $(function (){
        fc.parent = true
        fc.def(["Title","Content"])
        $("form").submit(function(){
            var ok = fc.chk("Title",function(title){
                if(title.length===0){
                    return "标题不可为空"
                } else {
                    return true
                }
            })
            ok = ok && fc.chk("Content",function(content){
                if(content.length===0){
                    return "内容不能为空"
                } else {
                    return true
                }
            })
            return ok
        })



        $(".form-horizontal").submit(function(){
            var ids = []
            $(".attachment-del-btn").each(function(){
                ids.push(parseInt($(this).attr("data-id"),10))
            })
            $("#files_id").val(JSON.stringify(ids))
            return true
        })

        function OnAttachBtnClick(event){
            event.preventDefault();
            var idToDelete = $(this).attr("data-id")
            console.log("Del Btn Clicked "+idToDelete)
            var delete_url = "http://" + location.host + "/admin/Attachment/" + idToDelete;
			$.ajaxSetup({
	        	type: "DELETE",
	        	async: false
	        });
            var responseResult = $.ajax({url:delete_url}).responseText;
            if (responseResult!="false"){
                $(this).parent().parent().remove();
            }
        }

        $(".attachment-del-btn").click(OnAttachBtnClick)


        function UploadStart(file){
            var appendStr = "<tr id='r"+file.id+"'><td>"+file.name+"</td><td>上传中</td></tr>"
            console.log(appendStr)
            $("#AttachmentTHead").html(
            "<tr><th>文件名</th><th>操作</th></tr>"
            )
            $("#AttachmentTBody").append(
            appendStr
            )
            return true
        }

        function FileQueued(file){
            console.log(file)
            swfu.startUpload(file.id)
            return true
        }

        function UploadError(file,code,msg){
            console.log("On Upload Error "+code+" "+msg)
            return true
        }
        function UploadProgress(file,complete, total){
            $("#progressbar").attr("style","width: "+complete*100.0/total+"%;");
            return true
        }

        function UploadSuccess(file,data,resp){
            var id = JSON.parse(data)
            $("#r"+file.id).remove()
             var appendStr = "<tr id='r"+file.id+"'><td>"+file.name+"</td><td><button class='attachment-del-btn btn btn-danger' data-id='"+
             id
             +"'>删除</button></td></tr>"
            $("#AttachmentTBody").append(appendStr)
            $(".attachment-del-btn").click(OnAttachBtnClick)
            return true
        }

        function FileQueueError(file,errcode,msg){
            if(errcode===SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT ){
                alert("请选择小于20MB的文件")
            }
        }

        var upload_url = "http://" +  location.host + "/admin/Attachment/upload";
        swfu = new SWFUpload({
        	upload_url : upload_url,
        	flash_url : "http://"+location.host+ SWF_URL,
        	file_size_limit : "20MB",
			button_width: "63",
			button_height: "21",
            button_image_url : BTN_IMG,
			button_placeholder_id: "spanSWFUploadButton",
			button_text: '<span class="theFont">上传</span>',
			button_text_style: ".theFont { font-size: 12; }",
			button_text_left_padding: 12,
			button_text_top_padding: 3,
            upload_start_handler : UploadStart
        });
        swfu.fileQueued = FileQueued
        swfu.uploadError = UploadError
        swfu.uploadProgress = UploadProgress
        swfu.uploadSuccess = UploadSuccess
        swfu.fileQueueError =  FileQueueError
    })
})