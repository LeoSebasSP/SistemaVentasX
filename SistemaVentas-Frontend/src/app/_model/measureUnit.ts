export class MeasureUnit{
    id!: bigint;
    name!: string;
    description!: string;
    isEnabled!: boolean;
    baseMeasureUnit!: number;
    conversionFactor!: number;
    creationDate!: string;
    updateDate!: string;
    userCreatorId!: bigint;
    userUpdaterId!: bigint;
}