import React, {Component, useState, useEffect} from 'react';
import './table.css'
//import logo from './logo.svg';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {messages: ["none"]};
  } 



 isJSON(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}
  componentDidMount(){
      
      fetch('/api/array')
            .then(response => response.json())
            .then(json => {
        		
                console.log("json", json)
                console.log("typeof json", typeof json )
                console.log("isArray", Array.isArray(json)  )

  				// let parsedJSON = JSON.parse(json)
                // console.log(parsedJSON)
  				let finalJSON = turnArrayMessageIntoJSON(json)
                console.log(finalJSON)

            // console.log(JSON.parse(message[0])["death"]);
        	// let newMessage = message.map( (msg) => {
            // 	return JSON.parse(msg)
            // })
        
            // for(var key in message[0]){
            // console.log(key)
            // }
            // if (this.isJSON(message)){
                // console.log("is a json")
            // }else{
            //   console.log("is NOT a json")
            // }
                this.setState({messages:finalJSON})
            });
  }
  render() {
  return (
    <div>
    <center>
    <div class="header">
        <h1><b>Coronavirus</b></h1>
        
        </div>
        </center>

    <center>
    <img class ="logo" src = "/logo.svg"/>
    </center>

    <div class="table">
		<div class="row tableHead">
			<div>State</div>
			<div>Death</div>
			<div>Data</div>
            <div>hospitalized</div>
		</div>
        {
          this.state.messages.map((msg)=>{
            return (<div class="row ">
            <div>{msg.stateName } </div>
			<div>{msg.death }</div>
			<div>{msg.dataQualityGrade }</div>
            <div>{msg.hospitalizedCurrently || 0}</div>
              
          </div>)})
          }
    </div>
    </div>);
  }
}

function turnArrayMessageIntoJSON(json) {
    var newJSON = []
    for(var x = 0; x < json.length; x++){

    	let state = json[x]
	 	var objName = ""
        var newObj = {}

        for(var y = 0; y < state.length; y++){

        	let item = state[y]

            if (item.length == 1) {
                objName = item[0]
                newObj.stateName = item[0]
            } else {
                newObj[item[0]] = item[1]
            }
            
        }
        newJSON.push(newObj)
        // newJSON[objName] = newObj
        
    }
	return newJSON
}
export default App;

