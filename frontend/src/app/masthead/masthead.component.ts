import { Component} from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MainService } from '../mainsv/main.service';
import { UserinfoComponent } from './userinfo/userinfo.component';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { OptionModule, isSomeAndNotNull } from '../option/option.module';

@Component({
    selector: 'masthead',
    templateUrl: './masthead.component.html',
    styleUrls: ['./masthead.component.scss'],
    standalone: true,
    imports: [
        CommonModule,
        MatToolbarModule,
        UserinfoComponent,
        MatButtonModule,
        OptionModule
    ]
})
export class MastheadComponent {

    protected isMobileLayout: boolean = false;

    constructor(protected readonly mainService: MainService) {}

    ngOnInit() {
        window.onresize = () => {this.isMobileLayout = window.innerWidth <= 640; console.log(window.innerWidth)};
    }

    protected titleClicked() {
        this.mainService.redirectToHomepagePage();
    }

}
