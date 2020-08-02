var express = require('express');
var mysql = require('mysql');
var dbconfig = require('./config/database.js');
var connection = mysql.createConnection(dbconfig);

var app = express();

app.set('port', process.env.PORT || 3000);

app.get('/', function(req, res){
    console.log("111");
    res.send('Root');
});

app.get('/museums', function(req, res){
    var m_name = req.query.name;
    //[m_name]
    connection.query('SELECT * FROM museum WHERE name = ?', [m_name], function(error, rows){
        if(error) throw error;
        console.log('Museums info are : ', rows);
        res.send({"status": "true", "data": rows});
    });
});

app.get('/relics', function(req, res){
    var m_name = req.query.museum_name;    
    connection.query('SELECT * FROM relic WHERE museum_name = ?', "국립제주박물관", function(error, rows){
        if(error) throw error;
        
        res.send({"status": "true", "data": rows});
    });
    
});

app.get('/events', function(req, res){
    var m_name = req.query.museum_name;
    connection.query('SELECT * FROM event WHERE museum_name = ?', [m_name], function(error, rows){
        if(error) throw error;
        
        res.send({"status": "true", "data": rows});
    });
});

app.get('/books', function(req, res){
    var m_name = req.query.museum_name;
    connection.query('SELECT * FROM book WHERE museum_name = ?', [m_name], function(error, rows){
        if(error) throw error;
        
        res.send({"status": "true", "data": rows});
    });
});

app.post("write", function(req,res){
    
});


app.listen(app.get('port'), function(){
    console.log('Express server listening on port ' + app.get('port'));
});

//서버 ip 알아오는 함수
var os = require('os');
  
function getServerIp() {
    var ifaces = os.networkInterfaces();
    var result = '';
    for (var dev in ifaces) {
        var alias = 0;
        ifaces[dev].forEach(function(details) {
            if (details.family == 'IPv4' && details.internal === false) {
                result = details.address;
                ++alias;
            }
        });
    }
  
    return result;
}
  
console.log(getServerIp());