function imagePreview() {
    var fileInput =  document.getElementById('multipartFile');
    var filePath = fileInput.value;

    // Image preview
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            document.getElementById('imagePreview').innerHTML =
                '<img src="' + e.target.result + '" class="rounded mt-3" style="max-width: 300px;" />';
        };

        reader.readAsDataURL(fileInput.files[0]);
        document.getElementById('fileLabel').innerHTML = fileInput.files[0].name;

        var submitBtn = document.getElementById('submitBtn');
        if (submitBtn != null) {
            submitBtn.disabled = false;
        }
    }
    else {
        document.getElementById('submitBtn').disabled = true;
    }
}