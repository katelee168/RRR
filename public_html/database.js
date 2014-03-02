var db = require('./models/db');
var User = db.User;
var connect = db.connect;

connect.once('open', function callback() {

	app.get('/', function(req, res) {
		res.send('Hello World\n');
		});
	app.get('/about', function(req, res){
		res.send('stuff');
	});
	
	app.get('/test', function(req, res){
		res.send ('stuff');
	});
	app.get('/database', function(req, res){
		res.send('My name is" + req.params.myname);
	});
	
	app.get('/database/:user/:password', function(req, res) {
		var newUser = new User{
					"username":req.params.user,
					"password":req.params.user});
		newUser.save(function(e){
			if(e) {
				console.log(e);
				res.send('FAIL!')
			}
			else{
				res.send('SUCCESS!');
			}
		});
	});
	app.get('/findMe/:user', function(req, res){
		User.findOne({"username":"Collin"}, function(err, user){
					if  (err) {
						res.send('didnt find');
					}
					else{ res.send('found');}
		});
});

});

/*Jade*/
app.set(