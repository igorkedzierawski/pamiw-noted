import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output, Pipe, PipeTransform } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { Note } from 'src/app/mainsv/data/note';
import { Option, OptionModule, map } from 'src/app/option/option.module';

@Pipe({name: 'simplifyDate'})
export class SimplifyDatePipe implements PipeTransform {
    transform(dateIso8601: string): string {
        if (!dateIso8601) {
            return '';
        }
      
        const date = new Date(dateIso8601);
        const year = date.getFullYear();
        const month = ('0' + (date.getMonth() + 1)).slice(-2);
        const day = ('0' + date.getDate()).slice(-2);
        const hours = ('0' + date.getHours()).slice(-2);
        const minutes = ('0' + date.getMinutes()).slice(-2);

        return `${year}-${month}-${day} ${hours}:${minutes}`;
    }
}

@Component({
    selector: 'notepreview',
    templateUrl: './notepreview.component.html',
    styleUrls: ['./notepreview.component.scss'],
    standalone: true,
    imports: [
        MatListModule,
        CommonModule,
        OptionModule
    ],
})

export class NotepreviewComponent {

    @Input() note: Option<Note>;

    @Output() noteTitleClicked = new EventEmitter<number>();

    protected simplifiedCreationDate(): Option<string> {
        return map(this.note?.createdAt, this.simplifyDate);
    }

    protected simplifiedEditionDate(): Option<string> {
        return map(this.note?.editedAt, this.simplifyDate);
    }

    private simplifyDate(dateIso8601: string): string {
        if (!dateIso8601) {
            return '';
        }
      
        const date = new Date(dateIso8601);
        const year = date.getFullYear();
        const month = ('0' + (date.getMonth() + 1)).slice(-2);
        const day = ('0' + date.getDate()).slice(-2);
        const hours = ('0' + date.getHours()).slice(-2);
        const minutes = ('0' + date.getMinutes()).slice(-2);

        return `${year}-${month}-${day} ${hours}:${minutes}`;
    }

}
