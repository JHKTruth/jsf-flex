/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

//create a task and sub class it so to show knowledge
(function() {
	var nsPath = ["playground", "jsfFlex", "googlecode", "com"],
		nsPathLength = nsPath.length, nsTemp, ns = window || {};
	
	while(nsPathLength--){
		if(typeof (nsTemp = ns[nsPath[nsPathLength]]) === "undefined"){
			ns[nsPath[nsPathLength]] = {},
				ns = ns[nsPath[nsPathLength]];
		}else if(typeof nsTemp != "object"){
			throw new Error(nsPath.slice(nsPathLength).join(".") + " exists but is not of type object");
		}
	}
	
	ns._BaseTask = function(request) {
		this.data = null;
		this.request = request;
	};
	
	ns._BaseTask.RESULT_CODE = {
			SUCCESS: 1,
			ERROR: 0
	};
	
	ns._BaseTask.prototype.printData = function() {
		console.info("Once again just for playing around ", this.data);
		
	};
	
	ns._BaseTask.prototype.performTask = function() {
		throw new Error("Should be implemented by sub classes");
	};
	
	ns.GeolocationTask = function(request) {
		ns._BaseTask.apply(this, arguments);
	};
	ns.GeolocationTask.prototype = new ns._BaseTask();
	ns.GeolocationTask.prototype.constructor = ns.GeolocationTask;
	
	ns.GeolocationTask.ERROR_CONDITIONS = ["Permission denied", "Position is not available", "Request timeout"];
	
	ns.GeolocationTask.prototype.performTask = function() {
		
		var _this = this;
		navigator.geolocation.getCurrentPosition( function(position) {
	        console.info("Found ", position);
	        _this.data = position;
	        _this.data.taskResult = ns._BaseTask.RESULT_CODE.SUCCESS;
	        _this.data.instance = _this;
	        _this.printData();
	        
	        postMessage(JSON.stringify(_this.data));
	        }, function(error) {
	            _this.data = null;
	            console.info("Error with : ", error, ns.GeolocationTask.ERROR_CONDITIONS[parseInt(error, 10) - 1]);
	            postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.ERROR, error: error, instance: _this}));
	            }, {
	                enableHighAccuracy: true,
	                timeout: 2000, 
	                maximumAge: 0
	                } );
	};
	
	ns.FibonacciComputeLongTask = function(request) {
		ns._BaseTask.apply(this, arguments);
	};
	ns.FibonacciComputeLongTask.prototype = new ns._BaseTask();
	ns.FibonacciComputeLongTask.prototype.constructor = ns.FibonacciComputeLongTask;
	
	ns.FibonacciComputeLongTask.cache = {};
	
	ns.FibonacciComputeLongTask.prototype.performTask = function() {
		var computeRequest = this.request.computeRequest;
		if(typeof computeRequest === "undefined") {
			throw new Error("computeRequest field was not passed");
		}else {
			this.data = this.fibonacci(computeRequest);
	        this.data.taskResult = ns._BaseTask.RESULT_CODE.SUCCESS;
	        this.data.instance = this;
	        this.printData();
	        postMessage(JSON.stringify(this.data));
		}
	};
	
	ns.FibonacciComputeLongTask.prototype.fibonacci = function(val) {
		/*
		 * Of course not even in the least optimal solution [Strassen's algorithm is the correct one], 
		 * but this is just to test out the caching.
		 */
		if(val < 2) {
			return val;
		}
		
		var computedVal = -1;
		if(ns.FibonacciComputeLongTask.cache.hasOwnProperty(val)){
			computedVal = ns.FibonacciComputeLongTask.cache[val];
		}else {
			computedVal = this.fibonacci(val-1) + this.fibonacci(val-2);
			ns.FibonacciComputeLongTask.cache[val] = computedVal;
		}
		
		return computedVal;
	};
	
	ns.WebSocket = function(request) {
		ns._BaseTask.apply(this, arguments);
	};
	ns.WebSocket.prototype = new ns._BaseTask();
	ns.WebSocket.prototype.constructor = ns.WebSocket;
	
	ns.WebSocket.prototype.performTask = function() {
		
		if(typeof WebSocket !== "undefined") {
			if(typeof this.request.url !== "undefined") {
				var ws = new WebSocket("ws://localhost/webSocket:8080/");
				ws.onmessage = function(event) {
					ws.data = JSON.parse(event.data);
					ws.data.taskResult = ns._BaseTask.RESULT_CODE.SUCCESS;
					ws.data.instance = ws;
					ws.printData();
					
					postMessage(JSON.stringify(ws.data));
				};
				
				ws.onclose = function() {
					ws.send(JSON.stringify({
						action: "closing"
					}));
				};
				
				ws.onopen = function() {
					ws.send(JSON.stringify({
						action: "opening"
					}));
				};
			}else {
				throw new Error("Field of url was not provided to the request");
			}
			throw new Error("WebSocket is not defined");
		}
		
	};
	
	ns.WebSQL = function(request) {
		ns._BaseTask.apply(this, arguments);
	};
	ns.WebSQL.prototype = new ns._BaseTask();
	ns.WebSQL.prototype.constructor = ns.WebSQL;
	
	ns.WebSQL.prototype.performTask = function() {
		
		try{
			if(typeof window.openDatabase === "undefined"){
				throw new Error("window.openDatabase is not defined/WebSQL is not supported for the current browser");
			}
			
			var _this = this;
			var req = this.request;
			if(typeof req.sql !== "undefined" &&
					typeof req.dbName !== "undefined" && 
					typeof req.dbVersion !== "undefined" && 
					typeof req.dbSize !== "undefined") {
				
				var db = openDatabase(req.dbName, req.dbVersion, (req.dbDescription || ""), req.dbSize);
				db.transaction(function(tx) {
					tx.executeSql(req.sql, (req.arguments || []), function(tx, results) {
						_this.data = results;
						_this.data.tx = tx;
						_this.data.taskResult = ns._BaseTask.RESULT_CODE.SUCCESS;
						_this.data.instance = _this;
				        _this.printData();
				        postMessage(JSON.stringify(_this.data));
				        
					}, function(tx, error) {
						_this.data = null;
						postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.ERROR, error: error, instance: _this, tx: tx}));
					});
				});
				
			}else {
				throw new Error("Required parameters of: sql, dbName, dbVersion, dbSize is/are missing");
			}
			
		}catch(error) {
			console.info("errored out during the DB execution");
			throw error;
		}
		
	};
	
})();