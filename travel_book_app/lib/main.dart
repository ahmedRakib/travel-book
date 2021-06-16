

import 'package:flutter/material.dart';
import 'package:travel_book_app/ui/login_screen.dart';

void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    theme: ThemeData(
      primaryColor: Colors.teal,
      accentColor: Colors.deepPurple,
      scaffoldBackgroundColor: Color(0xFFF3F5F7),
    ),
    home:  LoginScreen(),
  ),
  );
}

