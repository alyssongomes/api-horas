package br.ufc.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.api.model.HourCommand;
import br.ufc.api.model.HourId;

@Repository
@Transactional
public interface IHourRepositoryCommand extends JpaRepository<HourCommand, HourId>{}

