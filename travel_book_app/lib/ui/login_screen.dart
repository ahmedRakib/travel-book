import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:travel_book_app/service/api_service.dart';
import 'package:travel_book_app/ui/register_screen.dart';

import 'home_screen.dart';

final GlobalKey<ScaffoldState> _scaffoldState = GlobalKey<ScaffoldState>();

class LoginScreen extends StatefulWidget {
  @override
  _LoginScreenState createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  //controller for controlling the text fields
  TextEditingController _controllerForEmail = TextEditingController();
  TextEditingController _controllerForPassword = TextEditingController();

  bool _isLoading = false;
  ApiService _apiService = ApiService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key:  _scaffoldState,
      //backgroundColor: Theme.of(context).primaryColor,
      body: Container(
        decoration: BoxDecoration(
            gradient: LinearGradient(
                begin: Alignment.topRight,
                end: Alignment.bottomLeft,
                colors: [
              Theme.of(context).primaryColor,
              Theme.of(context).accentColor,
            ])),
        alignment: Alignment.center,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(
              "Travel Book",
              style: TextStyle(
                fontSize: 50.0,
                color: Colors.white,
              ),
            ),
            SizedBox(height: 45.0),
            Text('Login',style: TextStyle(color: Colors.cyan, fontSize: 25.0,fontWeight: FontWeight.bold),),
            SizedBox(height: 10.0),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Container(
                  decoration: BoxDecoration(
                      borderRadius: BorderRadius.all(Radius.circular(20)),
                      color: Colors.white),
                  child: _buildTextFieldForEmail()),
            ),
            SizedBox(height: 15.0),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Container(
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.all(Radius.circular(20)),
                    color: Colors.white),
                child: _buildTextFieldForPassword(),
              ),
            ),
            SizedBox(height: 15.0),
            SizedBox(
              width: 200,
              height: 50,
              child: RaisedButton(
                  shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(24.0)),
                  onPressed: () {
                    String Email = _controllerForEmail.text.toString();
                    String pass = _controllerForPassword.text.toString();
                    print("EMail:" + Email + "PAss:" + pass);
                    //Student student = Student(name: name, age: age, email: email);
                    _apiService.login().then((isSuccess){
                      setState(() => _isLoading = false);
                      if(isSuccess){
                        Navigator.push(_scaffoldState.currentState.context, MaterialPageRoute(builder: (BuildContext context){
                          return HomeScreen();
                        }));
                      }
                      else{
                        // _scaffoldState.currentState.showSnackBar(SnackBar(
                        //   content: Text("Login failed"),
                       // ));
                      }
                    });
                  },
                  color: Colors.cyan,
                  padding: EdgeInsets.all(12),
                  child: Text(
                    "Login",
                    style: TextStyle(fontSize: 24, color: Colors.white),
                  )),
            ),
            SizedBox(height: 15.0),
            GestureDetector(
              onTap: (){
                Navigator.push(_scaffoldState.currentState.context, MaterialPageRoute(builder: (BuildContext context){
                  return RegisterScreen();
                }));
              },
              child: Text.rich(TextSpan(
                  text: 'Don\'t have an account? ',
                  style: TextStyle(fontSize: 19.0, color: Colors.white),
                  children: [
                    (TextSpan(
                        text: 'Sign Up!',
                        style: TextStyle(color: Colors.lightBlue)))
                  ])),
            )
          ],
        ),
      ),
    );
  }

  Widget _buildTextFieldForEmail() {
    //A text field lets the user enter text
    return TextField(
      controller: _controllerForEmail,
      keyboardType: TextInputType.text,
      style: TextStyle(
        fontSize: 20.0,
      ),
      textAlign: TextAlign.left,
      decoration: InputDecoration(
        hintText: 'Email',
        prefixIcon: Icon(Icons.email),
        contentPadding: EdgeInsets.fromLTRB(30.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(20.0)),
      ),
    );
  }

  Widget _buildTextFieldForPassword() {
    return TextField(
      controller: _controllerForPassword,
      obscureText: true,
      style: TextStyle(
        fontSize: 20.0,
      ),
      decoration: InputDecoration(
        hintText: "Password",
        prefixIcon: Icon(Icons.visibility_off),
        contentPadding: EdgeInsets.fromLTRB(30.0, 10.0, 20.0, 10.0),
        border: OutlineInputBorder(borderRadius: BorderRadius.circular(20.0)),
      ),
    );
  }
}
