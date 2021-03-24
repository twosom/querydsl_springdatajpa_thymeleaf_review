let main = {
    init: function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },

    save: function () {
        let data = {
            name: $('#name').val(),
            city: $('#city').val(),
            street: $('#street').val(),
            zipcode: $('#zipcode').val()
        };
        $.ajax({
            type: 'POST',
            url: '/members/new',
            dataType: 'text',
            contentType: 'application/json',
            data: JSON.stringify(data)

        }).done(function () {
            alert('회원 가입이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

main.init();