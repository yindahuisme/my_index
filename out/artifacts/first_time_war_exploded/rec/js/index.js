function insert_string(varstring) {

}





// ¡ƒÃÏΩÁ√Ê«–ªª
function click_start_chat(obj){
    talkingto= obj.innerHTML
    document.getElementById("chat_title").innerHTML=talkingto;
    document.getElementById("chat_state1").style.visibility="hidden";
    document.getElementById("chat_state2").style.visibility="visible";

}
