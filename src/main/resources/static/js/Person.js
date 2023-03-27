/* 
Person 使用
 */ 
    var message = /*[[${list}]]*/ 'default';
    console.log(message);
    
    async function updatehis(){
    	await window.history.go(0);
    	await alert('刷新成功');
    };

    async function test(i){
    	console.log(i);
    };
    
    function createPerson(){
    	//window.location.href="addForm";
    	window.location.href = 'addForm';
    }

    function updatePerson(i){
    	console.log(i);
    	var pa = {"qid":i};
    	$.ajax({
            async:true,
            type: "GET",
            data: pa,
             url: "updateForm",
            success: function(res){
            	if (confirm('操作成功!') == true){
            		location.href = "editForm?action=edit&id="+pa.qid;
            	} else {
            		window.history.go(0);
            	}
            }, 
            error: function(e){
            	alert('Error'+e);
            }
     })
    };

    function delPerson(i){
    	console.log(i);
    	var pa = {"qid":i};
    	$.ajax({
            async:true,
            type: "POST",
            data: pa,
             url: "delPerson",
            success: function(res){
            	alert('刪除成功!');
            	history.go(0);
            }, 
            error: function(e){
            	alert('Error'+e);
            }
     })
    };
