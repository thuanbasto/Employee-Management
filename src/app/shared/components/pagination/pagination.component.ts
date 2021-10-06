import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {
  @Input() arrLength: number;
  @Input() size: number;
  @Input() page: number;
  @Input() pageTotal: number;
  @Input() numberTotal: number;

  @Output() changePage = new EventEmitter<any>();

  constructor() {
  }

  ngOnInit() {
    // console.log("paging : init " ,this.size, this.page, this.pageTotal, this.numberOfTotal);
  }

  prePage() {
    if (this.page > 1) {
      this.page -= 1;
    }
    this.emitValue();
  }

  nextPage() {
    if (this.page < this.pageTotal) {
      this.page += 1;
    }
    this.emitValue();
  }

  movePage(page: any) {
    if (page > 0 && page <= this.pageTotal) {
      this.page = parseInt(page);
      this.emitValue();
    }
  }

  changeSize() {
    this.page = 1;
    this.emitValue();
  }

  emitValue() {
    this.changePage.emit({
      size : this.size,
      page : this.page,
    });
  }

  generatePageNumber() {
    let pageArr = [];
    let delta = 2;

    for (let i = Math.max(2, this.page - delta); i <= Math.min(this.pageTotal - 1, this.page + delta); i++) {
      pageArr.push(i)
    }

    if (this.page - delta > 2) {
      pageArr.unshift("...")
    }
    if (this.page + delta < this.pageTotal - 1) {
      pageArr.push("...")
    }

    if (this.pageTotal >= 1) {
      pageArr.unshift(1)
    }

    if (this.pageTotal > 1) {
      pageArr.push(this.pageTotal)
    }

    return pageArr;
  }
}
