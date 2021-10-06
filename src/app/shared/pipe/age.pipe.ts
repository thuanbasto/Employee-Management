import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: "age",
})
export class AgePipe implements PipeTransform {
  transform(value: any): string {
    let html = new Date().getFullYear() - new Date(value).getFullYear() + "";
    return html;
  }
}
