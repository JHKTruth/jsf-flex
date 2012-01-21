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

/*
 * This file is mainly for documentation purpose. Meaning I am playing around 
 * with some of the content so most of the code is not complete as I wanted 
 * something to reference by rather than books + online documentation.
 */
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
		this.request = JSON.parse(request);
	};
	
	ns._BaseTask.RESULT_CODE = {
			SUCCESS: 1,
			ERROR: 0
	};
	
	ns._BaseTask.prototype.genericError = function(event) {
		postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.ERROR, error: event, instance: this}));
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
	
	ns.GeolocationTask.ERROR_CONDITIONS = ["Unknown", "Permission denied", "Position is not available", "Request timeout"];
	
	ns.GeolocationTask.prototype.performTask = function() {
		
		var _this = this;
		navigator.geolocation.getCurrentPosition( function(position) {
	        _this.data = position;		//{coords: {latitude: "", longitude: "", altitude: "", accuracy: "", altitudeAccuracy: "", heading: "", speed: ""}, timestamp: ""}
	        _this.data.taskResult = ns._BaseTask.RESULT_CODE.SUCCESS;
	        _this.data.instance = _this;
	        _this.printData();
	        
	        postMessage(JSON.stringify(_this.data));
	        }, function(error) {
	            console.info("Error with : ", error, ns.GeolocationTask.ERROR_CONDITIONS[parseInt(error, 10) - 1]);
	            postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.ERROR, error: error, instance: _this}));
	            }, {
	                //enableHighAccuracy: true, => used for Mobile devices that access the GPS application.
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
				var ws = new WebSocket(this.request.url);
				
				ws.addEventListener("message", function(event) {
					ws.data = JSON.parse(event.data);
					ws.data.taskResult = ns._BaseTask.RESULT_CODE.SUCCESS;
					ws.data.instance = ws;
					ws.printData();
					
					postMessage(JSON.stringify(ws.data));
				}, false);
				
				ws.addEventListener("close", function() {
					ws.send(JSON.stringify({
						action: "closing"
					}));
				}, false);
				
				ws.addEventListener("open", function() {
					ws.send(JSON.stringify({
						action: "opening"
					}));
				}, false);
			}else {
				throw new Error("Field of url was not provided to the request");
			}
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
			
			var _this = this,
				req = _this.request;
			
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
	
	ns.IndexedDB = function(request) {
		ns._BaseTask.apply(this, arguments);
	};
	ns.IndexedDB.prototype = new ns._BaseTask();
	ns.IndexedDB.prototype.constructor = ns.IndexedDB;
	
	ns.IndexedDB.prototype.dbOpenSuccess = function(event) {
		this.db = event.result || event.target.result;
		
		if(this.db.version === ''){
			var versionRequest = this.db.setVersion(this.request.db.version);
			versionRequest.addEventListener("success", this.dbCreateDBSuccess, false);
			versionRequest.addEventListener("error", this.genericError, false);
		}else{
			this.performTransactionTask();
		}
	};
	
	ns.IndexedDB.prototype.dbCreateDBSuccess = function(event) {
		this.db = event.result || event.target.result;
		var request = this.request;
		
		if(request.db.objectStores) {
			
			var objectStores = request.db.objectStores, 
				objectStoresLength = objectStores.length;
			objectStores.reverse();
			
	        while(objectStoresLength--) {
	        	var objectStore = objectStores[objectStoresLength], 
	        		createOSArgs = [objectStore.name];
	        	if(objectStore.keyPath) {
	        		createOSArgs.push({keyPath: objectStore.keyPath});
	        	}
	        	
	        	if(typeof objectStore.autoIncrement !== "undefined") {
	        		createOSArgs.push(objectStore.autoIncrement);
	        	}
	        	
	        	var store = this.db.createObjectStore.apply(this.db, createOSArgs);
	        	
	        	if(objectStore.indexes) {
	        		var indexes = objectStore.indexes, 
	        			indexesLength = indexes.length;
	        		indexes.reverse();
	        		
	        		while(indexesLength--) {
	        			var index = indexes[indexesLength], 
	        				createIndexArgs = [index.name, index.property];
	        			
	        			if(typeof index.unique !== "undefined") {
	        				createIndexArgs.push({unique: index.unique});
	    	        	}
	        			
	        			store.createIndex.apply(store, createIndexArgs);
	        		}
	        		
	        	}
	        }

	        this.performTransactionTask();
		}
		
	};
	
	ns.IndexedDB.prototype.performTransactionTask = function() {
		var request = this.request, 
			transactionRequests = request.transactions, 
			transactionRequestsLength = transactionRequests.length;
		transactionRequests.reverse();
		
		while(transactionRequestsLength--){
			var transactionRequest = transactionRequests[transactionRequestsLength], 
				transactionRequestArgs = [transactionRequest.spanObjectStores];
			
			if(typeof transactionRequest.transactionLevel !== "undefined") {
				transactionRequestArgs.push(transactionRequest.transactionLevel);
        	}
        	
        	var transaction = this.db.transaction.apply(this.db, transactionRequestArgs);
			var objectStore = transaction.objectStore(transactionRequest.objectStore);
			
			this.performTransactionTaskHelper(transactionRequest.tasks, objectStore);
			
		}
	};
	
	ns.IndexedDB.prototype.performTransactionTaskHelper = function(tasks, objectStore) {

		var tasksLength = tasks.length,
			_this = this;
		tasks.reverse();
		
		while(tasksLength--){
			function() {
				var task = tasks[tasksLength];
				var taskRequest = objectStore[task.method].apply(objectStore, task.args || []);
				taskRequest.addEventListener("success", function(event) {
					postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.SUCCESS, data: event, instance: _this}));
					if(typeof task.tasks !== "undefined") {
						//for cases such as openCursor and etcetera
						_this.performTransactionTaskHelper(task.tasks, objectStore);
					}
					
					var result = event.result || event.target.result;
					if(result && result["continue"]) {
						//means of cursor so invoke continue
						result["continue"]();
					}
				}, false);
			}();
		}
    	
	};
	
	ns.IndexedDB.prototype.performTask = function() {
		
		try{
			var indexedDB = window.mozIndexedDB || window.webkitIndexedDB;
			
			if(typeof indexedDB !== "undefined") {
				var req = this.request;
				if(typeof req.db !== "undefined" && req.db.name !== "undefined") {
					
					var db = indexedDB.open(req.db.name);
					db.addEventListener("success", this.dbOpenSuccess, false);
					db.addEventListener("error", this.genericError, false);
					
				}else {
					throw new Error("Required parameters of: dbName is/are missing");
				}
			}
		}catch(error) {
			console.info("errored out during the IndexedDB execution");
			throw error;
		}
		
	};
	
	//FILE API, just for reference
	ns._FileTask = function(request) {
		ns._BaseTask.apply(this, arguments);
	};
	ns._FileTask.prototype = new ns._BaseTask();
	ns._FileTask.prototype.constructor = ns._FileTask;
	
	ns._FileTask.prototype.performTask = function() {
		//TODO
	};
	
	ns.FileTaskAPI = function(request) {
		ns._FileTask.apply(this, arguments);
	};
	ns.FileTaskAPI.prototype = new ns._FileTask();
	ns.FileTaskAPI.prototype.constructor = ns.FileTaskAPI;
	
	ns.FileTaskAPI.prototype.performTask = function() {
		
		var _this = this,
			req = _this.request;
		//Later play w/ Blob
		if(typeof req.fileTasks !== "undefined") {

			if(FileReader) {
				var fileTasks = req.fileTasks,
					fileTasksLength = fileTasks.length;
				fileTasks.reverse();
				
				while(fileTasksLength--){
					var reader = new FileReader(), 
						fileTask = fileTasks[fileTasksLength];
					//should use onload since the listeners might not be implemented yet 
					reader.addEventListener("load", function(event){
						var result = event.result || event.target.result;
						postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.SUCCESS, data: result, instance: _this}));
					}, false);
					reader.addEventListener("error", this.genericError, false);
					reader[fileTask.method].apply(reader, fileTask.args || []);
					
				}
	    	}
			
		}else {
			throw new Error("Required parameters of: fileTasks is/are missing");
		}
		
	};
	
	ns.FileExpansionTaskAPI = function(request) {
		this.root = null;
		ns._FileTask.apply(this, arguments);
	};
	ns.FileExpansionTaskAPI.prototype = new ns._FileTask();
	ns.FileExpansionTaskAPI.prototype.constructor = ns.FileExpansionTaskAPI;
	
	ns.FileExpansionTaskAPI.prototype.performTask = function() {
		
		var _this = this,
			req = _this.request;
		
		if(typeof req.fileTasks !== "undefined" && 
				typeof req.fileSystemInfo !== "undefined") {
			
			if(requestFileSystem) {
				var fileSystemInfo = req.fileSystemInfo;
				
				requestFileSystem(fileSystemInfo.type, fileSystemInfo.size, function(event) {
					
					_this.root = event.root;
					_this.performFileTask(req.fileTasks);
				}, this.genericError);
				
			}
			
		}else {
			throw new Error("Required parameters of: fileTasks and fileSystemInfo is/are missing");
		}
		
	};
	
	ns.FileExpansionTaskAPI.prototype.performFileTask = function(fileTasks) {
		if(!fileTasks) {
			return;
		}
		var fileTasksLength = fileTasks.length;
		fileTasks.reverse();
		
		/*
		 * Many of the File Expansion APIs require the previous result and since this 
		 * is mainly for documentation purpose am leaving out those cases. Perhaps in the 
		 * future when I get bored.
		 */
		while(fileTasksLength--){
			
			var fileTask = fileTasks[fileTasksLength], 
				fileTaskArgs = fileTask.args || [],
				_this = this;
			
			function() {
				//Quite a deal of trouble just for documentation
				if(fileTask.method === "createReader" && 
					fileTasksLength > 0 && 
					fileTasks[fileTasksLength - 1] === "readEntries") {
					fileTasksLength--;
					
					var reader = _this.previousResult.createReader();
					//the readEntries method reads the list of entries by blocks
					var read = function() {
						reader.readEntries(function(files) {
							if(files.length){
								postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.SUCCESS, data: files, instance: _this}));
								read();
							}else{
								_this.performFileTask(fileTask.fileTasks);
							}
						}, this.genericError);
					};
				}else {
					fileTaskArgs.push(function(event) {
						
						_this.previousResult = event;
						postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.SUCCESS, data: event, instance: _this}));
						_this.performFileTask(fileTask.fileTasks);
					});
					
					fileTaskArgs.push(this.genericError);
				}
				
				this.root[fileTask.method].apply(this.root, fileTaskArgs);
			}();
		}
	};
	
	ns.FileContentTaskAPI = function(request) {
		ns.FileExpansionTaskAPI.apply(this, arguments);
	};
	ns.FileContentTaskAPI.prototype = new ns.FileExpansionTaskAPI();
	ns.FileContentTaskAPI.prototype.constructor = ns.FileContentTaskAPI;
	
	ns.FileContentTaskAPI.prototype.performFileTask = function(fileTasks) {
		if(!fileTasks) {
			return;
		}
		var fileTasksLength = fileTasks.length;
		fileTasks.reverse();
		
		while(fileTasksLength--){
			function() {
				var fileTask = fileTasks[fileTasksLength], 
					_this = this;
				
				this.root.getFile(fileTask.name, fileTask.args, function(entry) {
					entry.createWriter(function(writer){
						writer.addEventListener("writeend", function() {
							postMessage(JSON.stringify({taskResult: ns._BaseTask.RESULT_CODE.SUCCESS, data: fileTask, instance: _this}));
						}, false);
					}, this.genericError);
					
					var blobBuilder = BlobBuilder || WebKitBlobBuilder;
					if(blobBuilder) {
						var blob = new blobBuilder();
						blob.append(fileTask.text);
						writer.write(blob.getBlob());
					}
				}, this.genericError);
			}();
		}
	};
	
})();