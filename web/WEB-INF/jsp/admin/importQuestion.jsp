<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function() {
        var $ = jQuery,
                $list = $('#thelist'),
                $btn = $('#ctlBtn'),
                state = 'pending',
                uploader;

        var uploader = WebUploader.create({
            // swf文件路径
            swf: '${pageContext.request.contextPath}/webuploader/Uploader.swf',
            // 文件接收服务端。
            server: '${pageContext.request.contextPath}/admin/importQuestion.action',
            pick: '#picker',
        });

        uploader.on( 'fileQueued', function( file ) {
            $list.append( '<div id="' + file.id + '" class="item">' +
                    '<h4 class="info">' + file.name + '</h4>' +
                    '<p class="state">等待上传...</p>' +
                    '</div>' );
        });

        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                        '</div>' +
                        '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress .progress-bar');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<div class="progress progress-striped active">' +
                        '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                        '</div>' +
                        '</div>').appendTo( $li ).find('.progress-bar');
            }

            $li.find('p.state').text('上传中');

            $percent.css( 'width', percentage * 100 + '%' );
        });
        uploader.on( 'uploadSuccess', function( file ) {
            $( '#'+file.id ).find('p.state').text('已上传,并导入完成');
        });

        uploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('p.state').text('上传出错');
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });
        $btn.on( 'click', function() {
            if ( state === 'uploading' ) {
                uploader.stop();
            } else {
                uploader.upload();
            }
        });
    });
</script>
<style>
    #uploader{
        background-color: #c7c7c7;
        padding: 15px;
        border-radius: 10px;
    }
    #picker{
        width: 120px;
        float: left;
        text-align: center;
        border-radius: 6px;
    }
    #ctlBtn{
        margin-left:10px;
    }
    .list-group-item{
        font-size: 16px;
    }
</style>

<div class="main">
    <h1>Excel文件上传</h1>
    <ol class="list-group">
        <li class="list-group-item">
            <h2 class="list-group-item-heading">注意事项</h2>
        </li>
        <li class="list-group-item">文件名必须是以题型命名，如选择题（choice）、填空题（fillin）、简答题（essay）、问答题（discuss）</li>
        <li class="list-group-item">列名按照模版要求（包括：content、section、correctionFill、answer1、rightAnswer、diff）</li>
        <li class="list-group-item">diff列必须将单元格格式设置为“@”</li>
        <li class="list-group-item">只支持单个文件上传，一个文件只有一种题型</li>
    </ol>

    <div id="uploader" class="wu-example">
        <!--用来存放文件信息-->
        <div id="thelist" class="uploader-list"></div>
        <div class="btns">
            <div id="picker">选择文件</div>
            <div id="ctlBtn" class="btn btn-default">开始上传</div>
        </div>
    </div>
</div>
