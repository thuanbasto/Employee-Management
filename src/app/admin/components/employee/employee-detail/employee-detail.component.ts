import { User } from './../../../../shared/model/user';
import { HeartService } from './../../../../shared/services/heart.service';
import { AuthService } from './../../../../shared/services/auth.service';
import { URL_IMAGE } from '../../../../../environments/environment';
import { EmployeeService } from '../../../../shared/services/employee.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

declare var $ : any;

@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.css']
})
export class EmployeeDetailComponent implements OnInit {
  employee_id : number;
  employee : any;
  isHearted = false;
  numberOfHearts = 0;
  user : User;

  constructor(
    private employeeService: EmployeeService,
    private authService: AuthService,
    private heartService: HeartService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.employee_id = +this.route.snapshot.paramMap.get('id');

    if (this.employee_id) {
      this.employeeService.get(this.employee_id).subscribe(
        (response: any) => {
          this.employee = response;
          // console.log(response);

          if (this.employee.imageUrl == null) {
            this.employee.imageUrl = "assets/image/default-image.jpg";
          } else {
            this.employee.imageUrl = URL_IMAGE + "/" + this.employee.imageUrl ;
          }
          this.checkIsHearted();
          this.countHeart();
        },
        err => {
          console.log(err);
        }
      )
    }
  }

  countHeart() {
    this.heartService.countHeart(this.employee_id).subscribe(
      (res : number) => {
        console.log(res);
        this.numberOfHearts = res;
      },
      err => {
        console.log(err);
      }
    )
  }

  checkIsHearted() {
    if (this.authService.isLoggedIn()) {
      this.user = this.authService.currentUser;

      this.heartService.checkIsHearted(this.user.id, this.employee_id).subscribe(
        res => {
          console.log(res);
          this.isHearted = true;
          $("#btnHeart").css("background-color", "rgb(238, 50, 50)");
          $(".iHeart").css("color", "white");
        },
        err => {
          console.log(err);
        }
      );
    }
  }

  dropHeart() {
    if (!this.authService.isLoggedIn()) {
      Swal.fire({
        title: 'Login required!',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Login now!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.router.navigate(["/login"]);
        }
      })
    } else {
      if (!this.isHearted) {
        $(".heart").removeClass("d-none");
        $("#btnHeart").css("background-color", "rgb(238, 50, 50)");
        $(".iHeart").css("color", "white");
        setTimeout(() => {
          $(".heart").addClass("d-none");
        }, 1000)
        this.numberOfHearts++;
      } else {
        $(".iHeart").css("color", "rgb(238, 50, 50)");
        $("#btnHeart").css("background-color", "");
        this.numberOfHearts--;
      }
      this.isHearted = !this.isHearted;

      let heart = {
        heartId : {
          employee_id : +this.employee_id,
          user_id : this.user.id
        },
        status : this.isHearted
      }

      this.heartService.create(heart).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
        }
      )

    }
  }
}
