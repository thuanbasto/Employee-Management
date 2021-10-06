import { Message } from './../../../shared/model/message';
import { AuthService } from './../../../shared/services/auth.service';
import { AngularFireDatabase } from '@angular/fire/database';
import { Component, OnInit } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';

declare var $: any;

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  username = "";
  messages: [];

  constructor(
    public db: AngularFireDatabase,
    private firestore: AngularFirestore,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.username = this.authService.currentUser.username;
    $("#message").val("");
    $("#message").focus();

    $(document).ready(function(){
      $('#action_menu_btn').click(function(){
        $('.action_menu').toggle();
      });
    });
    this.firestore.collection("chats", ref => ref.orderBy('created_at').limit(10)).snapshotChanges().subscribe(
      res => {
        // res.forEach((a: any) => {
        //   console.log(a.payload.doc.data().created_at);
        // })
        console.log(res);
      }
    );

    $("#message").on("keydown", function (e) {
      if(e.keyCode == 13) { // enter key
        if(!e.shiftKey) { // enter key + shift key
          e.preventDefault();
          if ($("#message").val().trim() == "") {
            $("#message").val("");
          }
        }
      }
    })
  }

  addMessage() {
    if ($("#message").val().trim() != "") {
      let message = new Message(this.username, $("#message").val(), new Date().getTime());
      $("#message").val("");

      this.create({...message})
        .then(data => {
          console.log(data);
        })
    }
  }

  create(message) {
    return new Promise<any>((resolve, reject) => {
      this.firestore
        .collection("chats")
        .add(message)
        .then(res => {}, err => reject(err));
    });
  }

  trackByMessages(index, message) {
  }
}
