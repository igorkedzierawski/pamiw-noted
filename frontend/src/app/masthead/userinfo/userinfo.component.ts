import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { LoggedUser } from 'src/app/mainsv/data/loggeduser';

@Component({
    selector: 'userinfo',
    standalone: true,
    templateUrl: './userinfo.component.html',
    styleUrls: ['./userinfo.component.scss'],
    imports: [
        MatButtonModule, 
        MatMenuModule, 
        CommonModule, 
        MatIconModule,
        MatListModule
    ]
})
export class UserinfoComponent {

    @Input() user: LoggedUser | null = null;

    @Output() logoutButtonClicked = new EventEmitter<void>();

}
