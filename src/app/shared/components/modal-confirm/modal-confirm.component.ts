import { Modal } from './../../model/modal';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-modal-confirm',
  templateUrl: './modal-confirm.component.html',
  styleUrls: ['./modal-confirm.component.css']
})
export class ModalConfirmComponent implements OnInit {

  @Input() delete_id: any;
  @Input() modal: Modal;

  @Output() confirmEvent = new EventEmitter<any>();

  constructor() {
   }

  ngOnInit() {
  }


  confirm() {
    this.confirmEvent.emit();
  }
  
}
