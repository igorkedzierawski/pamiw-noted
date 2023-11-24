import { Component } from '@angular/core';
import { MainService } from '../mainsv/main.service';
import { MatButtonModule } from '@angular/material/button';
import { OptionModule } from "../option/option.module";
import { CommonModule } from '@angular/common';

@Component({
    selector: 'homepage',
    standalone: true,
    templateUrl: './homepage.component.html',
    styleUrls: ['./homepage.component.scss'],
    imports: [
        CommonModule,
        MatButtonModule,
        OptionModule
    ]
})
export class HomepageComponent {

    constructor(protected readonly mainService: MainService) {}

}
