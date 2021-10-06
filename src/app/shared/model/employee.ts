export class Employee {
  employee_id: number;
  name: string;
  address: string;
  description: string;
  phone: string;
  imageUrl: string;
  placeOfBirth: string;
  status: any;
  married: boolean;
  birthday: string;
  department: {
    department_id: string;
    departmentName: string;
  }
}
