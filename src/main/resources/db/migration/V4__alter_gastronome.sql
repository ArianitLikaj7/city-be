CREATE TABLE gastronome_attachments (
                                        gastronome_id SERIAL PRIMARY KEY,
                                        attachment VARCHAR(255)
);
ALTER TABLE gastronomies
    ADD COLUMN attachments jsonb;
