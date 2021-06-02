export interface DrivingSchool {
    id : number;
    version : number;
    name: string;
}

export class DrivingSchoolObject implements DrivingSchool {
    id!: number;
    version!: number;
    name!: string;
} 