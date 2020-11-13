<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/fabric.js/3.6.0/fabric.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui.code-snippet/v1.5.0/tui-code-snippet.min.js"></script>
<script type="text/javascript" src="https://uicdn.toast.com/tui-color-picker/v2.2.6/tui-color-picker.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.3/FileSaver.min.js"></script>
<script type="text/javascript" src="/image_editor/dist/tui-image-editor.js"></script>
<!-- <script type="text/javascript" src="./js/theme/white-theme.js"></script> -->
<script type="text/javascript" src="/image_editor/examples/js/theme/black-theme.js"></script>
<script>
 // Image editor
 var imageEditor = new tui.ImageEditor('#tui-image-editor-container', {
     includeUI: {
         loadImage: {
             path: '/image_editor/examples/img/sampleImage2.png',
             name: 'SampleImage'
         },
         theme: blackTheme, // or whiteTheme
         initMenu: 'filter',
         menuBarPosition: 'bottom'
     },
     cssMaxWidth: 700,
     cssMaxHeight: 500,
     usageStatistics: false
 });
 window.onresize = function() {
     imageEditor.ui.resizeEditor();
 }
</script>