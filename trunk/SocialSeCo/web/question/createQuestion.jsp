

<!DOCTYPE html>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create question</title>
        <script>

		
            function addColumn()
            {
		var table = document.getElementById("schema");
                var refChild = document.getElementById("button");
                var schemaNode = document.createElement("th");
                var thNode = document.createElement("input");
		var num = eval(document.getElementById("dimc").value);             
		var numrows = eval(document.getElementById("dimr").value);

		thNode.setAttribute("type", "text");
                thNode.setAttribute("name", "schema_"+ num );		
                thNode.onclick = addRow;
                schemaNode.appendChild(thNode);
                var br = document.createElement("br");
                schemaNode.appendChild(br);
                
		

                var thNode2 = document.createElement("input");
                thNode2.setAttribute("type", "text");
                thNode2.setAttribute("name", "type_"+ num);
                thNode2.onclick = addRow;
                schemaNode.appendChild(thNode2);
                
		
		document.getElementById("tr_0").insertBefore(schemaNode, refChild);
		
			

                for (i=1;i<numrows+1;i++)
                {
			
                    var newTd = document.createElement("td");
                    var newInput =  document.createElement("input");
                    newInput.setAttribute("type", "text");
                    newInput.setAttribute("name", "cell_"+ (i-1) +"_"+ num);
                    newInput.onclick = addRow;
                    newTd.appendChild(newInput);
		    document.getElementById("tr_"+i).appendChild(newTd);
                }

		
                
                
                num++;
                document.getElementById("dimc").value=num;
            }
            
            function addRow()
            {
		  
		var table = document.getElementById("schema");
                var currentTR = document.getElementById("tr_"+numrows);
		 var numrows = eval(document.getElementById("dimr").value);
		var numcols = eval(document.getElementById("dimc").value);

		 if("tr_"+numrows===this.parentNode.parentNode.id)
                {
  			
                        var newTR = document.createElement("tr");
			newTR.id = "tr_"+(numrows+1);
                        var dummyTD = document.createElement("td");
                        newTR.appendChild(dummyTD);
                        for(i=0;i<numcols; i++)
                        {
                            var newTD = document.createElement("td");
                            var newInput =  document.createElement("input");
                            newInput.setAttribute("type", "text");
                            newInput.setAttribute("name", "cell_"+ numrows +"_"+ i);
                            newInput.onclick = addRow;
                            newTD.appendChild(newInput);
                            newTR.appendChild(newTD);
                        }
                        table.lastChild.appendChild(newTR);
                        
                       
                        numrows++;
                        document.getElementById("dimr").value = numrows;
                    
                }
                
            }
            
            function removeColumn()
            {
                var num = eval(document.getElementById("dimc").value); 
                if(num>0)
                {
               
                    var numrows = eval(document.getElementById("dimr").value);
                    var firstTR = document.getElementById("tr_0");
                    var refChild = document.getElementById("button");
                    firstTR.removeChild(refChild.previousSibling);

                    for(i=1;i<numrows+1; i++)
                    {
                        var currentTR = document.getElementById("tr_"+i);
                        currentTR.removeChild(currentTR.lastChild);
                    }

                     num--;
                    document.getElementById("dimc").value=num;
                    if(num==0)
                    {
                        var table = document.getElementById("schema");
                        for(i=2;i<numrows+1; i++)
                        {
                            table.lastChild.removeChild(document.getElementById("tr_"+i));
                        }
                        document.getElementById("dimr").value=1;
                    }
                }
            }
        </script>
    </head>
    <body>
        <h1>Create question</h1>
         <div id="nav">
            <a href="../">Back</a>
         </div>
        <br/>
        <h2>Create question manually</h2>
        <div id="question">
            <form action="processQuestion.jsp" method="post">
                <div>Operation ID: <input type="text" value ="" name="opid" /> * </div>
                <div>Question: <input type="text" value ="" name="question"/> * </div>
                <div>Description: <input type="text" value ="" name="desc"/> </div>
                <div>Schema: * </div>
                <table id="schema">
                    <tr id="tr_0">
                        <th>
                    <div>Name: </div>
                    <br/>
                    <div>Type: </div>
                        </th>
                        <th id="button">
                            <input type="button" value="Append" name="columnAdder" onclick="addColumn()" /> 
                            <br/>
                            <input type="button" value="Remove" name="columnAdder" onclick="removeColumn()" /> 
                            
                        </th>
                        
                    </tr>
                    <tr id="tr_1">
                        <td>
                            <div><b>Instances:</b></div>
                        </td>
                        
                    </tr>
                </table>
                
                <div> <input type="submit" name="submit" value="insert" /> </div>
                <div><input id="dimc" type="hidden" name="cols" value="0" /> </div>
                <div><input id="dimr" type="hidden" name="rows" value="1" /> </div>
                
                <div> <p>* - mandatory fields  </p> </div>
            </form>
        </div>
        <br/>
        <br/>
        <h2>Import question from json</h2>
        <div id="import">
            <form action="importQuestion.jsp" method="post" enctype="multipart/form-data">
                <input type="file" name="importFile" size="40" />
                <input type="submit" name="other" value ="import"/>
            </form>
            
        </div>
        
        
       
    </body>
</html>