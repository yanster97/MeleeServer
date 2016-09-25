<!DOCTYPE html>
<html>
<head>
  <#include "header.ftl">

</head>


<body>
  <h1>Tournament Admin Page</h1>
    <input type="test" id="inputName" />
    <ul class="justList"></ul>
    <button id="btnName">Add player</button>
    <!--add field to enter player name
    make a button to add players
    display list of players
    button to submit players to server-->

<script>
$('#btnName').click(function(){
    var text = $('#inputName').val();
    if(text.length){
        $('<li />', {html: text}).appendTo('ul.justList')
    }
});
</script>
</body>



</html>

