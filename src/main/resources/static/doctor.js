$(function() {
    'use strict';

    var client;

    function showMessage(mesg)
    {
        var date = mesg.dateOfConsultation.split('T')[0];
        $('#messages').append('<tr>' +
            '<td>' + mesg.patientName + '</td>' +
            '<td>' + mesg.doctorName + '</td>' +
            '<td>' + date + '</td>' +
            '</tr>');
    }

    $("#seeHistory").click(function(){
        window.location.href="/findConsultations";
    });
    $("#addConsultationDescription").click(function(){
        window.location.href="/addConsultationDescription";
    });
    $("#goHome").click(function(){
        window.location.href="/doctorHome";
    });

    $('#from').on('blur change keyup', function(ev) {
        $('#connect').prop('disabled', $(this).val().length == 0 );
    });
    $('#connect,#disconnect,#text').prop('disabled', true);


    $(document).ready(function connect(){
        client = Stomp.over(new SockJS('/chat'));
        client.connect({}, function (frame) {
            $("#conversation").show();
            $('#text').focus();
            client.subscribe('/topic/messages', function (message) {
                showMessage(JSON.parse(message.body));
            });
        });
    });
});
